package cz.johnyapps.mutliplatform.library.game.domain

import cz.johnyapps.mutliplatform.library.game.data.GameSource
import cz.johnyapps.mutliplatform.library.game.domain.model.GameBoard
import cz.johnyapps.mutliplatform.library.game.domain.model.GameId
import kotlinx.coroutines.flow.Flow

interface GetGameBoardFlowUseCase {

    operator fun invoke(gameId: GameId): Flow<GameBoard?>
}

internal class LiveGetGameBoardFlowUseCase(
    private val gameSource: GameSource,
) : GetGameBoardFlowUseCase {

    override fun invoke(gameId: GameId): Flow<GameBoard?> {
        return gameSource.getBoardFlow(gameId)
    }
}
