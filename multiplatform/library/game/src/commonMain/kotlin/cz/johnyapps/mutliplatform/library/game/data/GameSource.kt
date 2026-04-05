package cz.johnyapps.mutliplatform.library.game.data

import cz.johnyapps.mutliplatform.library.game.data.model.GameNotFoundException
import cz.johnyapps.mutliplatform.library.game.domain.model.GameBoard
import cz.johnyapps.mutliplatform.library.game.domain.model.GameId
import cz.johnyapps.mutliplatform.library.game.domain.model.MakeMove
import cz.johnyapps.mutliplatform.library.game.domain.model.PlayerId
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

internal interface GameSource {

    suspend fun createGame(): GameId

    fun getBoardFlow(gameId: GameId): Flow<GameBoard>

    suspend fun makeMove(
        playerId: PlayerId,
        makeMove: MakeMove,
    )
}

internal class LiveGameSource : GameSource {

    private val games = mutableListOf<Game>()
    private val gamesMutex = Mutex()

    @OptIn(ExperimentalUuidApi::class)
    override suspend fun createGame(): GameId = gamesMutex.withLock {
        val gameId = GameId(
            gameId = Uuid.random().toString(),
        )

        val gameBoard = GameBoard(
            moves = emptySet(),
            size = GameBoard.Size(
                columns = 10,
                rows = 10,
            )
        )

        games += Game(
            id = gameId,
            board = MutableStateFlow(gameBoard),
        )

        return gameId
    }

    override fun getBoardFlow(gameId: GameId): Flow<GameBoard> {
        val game = games.firstOrNull { it.id == gameId }
            ?: throw GameNotFoundException(gameId)

        return game.board
    }

    override suspend fun makeMove(
        playerId: PlayerId,
        makeMove: MakeMove,
    ) = gamesMutex.withLock {
        val game = games.firstOrNull { it.id == makeMove.gameId }
            ?: throw GameNotFoundException(makeMove.gameId)

        game.board.update { currentBoard ->
            val updatedMoves = currentBoard.moves + GameBoard.Move(
                column = makeMove.field.column,
                row = makeMove.field.row,
                playerId = playerId,
            )

            currentBoard.copy(moves = updatedMoves)
        }
    }

    private data class Game(
        val id: GameId,
        val board: MutableStateFlow<GameBoard>,
    )
}
