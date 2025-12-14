package cz.johnyapps.mutliplatform.library.game.data

import cz.johnyapps.mutliplatform.library.game.data.model.GameNotFoundException
import cz.johnyapps.mutliplatform.library.game.domain.model.GameBoard
import cz.johnyapps.mutliplatform.library.game.domain.model.GameId
import cz.johnyapps.mutliplatform.library.game.domain.model.MakeMove
import cz.johnyapps.mutliplatform.library.game.domain.model.PlayerId
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

internal interface GameSource {

    suspend fun createGame(): GameId

    fun getBoardFlow(gameId: GameId): Flow<GameBoard?>

    suspend fun makeMove(
        playerId: PlayerId,
        makeMove: MakeMove,
    )
}

internal class LiveGameSource : GameSource {

    private val gameFlow = MutableStateFlow(listOf<Game>())

    @OptIn(ExperimentalUuidApi::class)
    override suspend fun createGame(): GameId {
        val gameId = GameId(
            gameId = Uuid.random().toString(),
        )

        val gameBoard = GameBoard(
            moves = emptySet(),
            size = GameBoard.Size(
                x = 10,
                y = 10,
            )
        )

        gameFlow.update { games ->
            games + Game(
                id = gameId,
                board = gameBoard,
            )
        }

        return gameId
    }

    override fun getBoardFlow(gameId: GameId): Flow<GameBoard?> {
        return gameFlow.map { games ->
            games.firstOrNull { it.id == gameId }
        }.map { game ->
            game?.board
        }.distinctUntilChanged()
    }

    override suspend fun makeMove(
        playerId: PlayerId,
        makeMove: MakeMove,
    ) {
        gameFlow.update { games ->
            val game = games.firstOrNull { it.id == makeMove.gameId }
                ?: throw GameNotFoundException(makeMove.gameId)

            val updatedMoves = game.board.moves + GameBoard.Move(
                x = makeMove.field.x,
                y = makeMove.field.y,
                playerId = playerId,
            )

            val updatedGame = game.copy(
                board = game.board.copy(
                    moves = updatedMoves,
                )
            )

            games.toMutableList()
                .apply { removeAll { it.id == makeMove.gameId } }
                .plus(updatedGame)
                .toList()
        }
    }

    private data class Game(
        val id: GameId,
        val board: GameBoard,
    )
}
