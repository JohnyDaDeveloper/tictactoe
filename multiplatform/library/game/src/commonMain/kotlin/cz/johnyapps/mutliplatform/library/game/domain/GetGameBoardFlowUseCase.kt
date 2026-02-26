package cz.johnyapps.mutliplatform.library.game.domain

import cz.johnyapps.mutliplatform.library.game.data.GameSource
import cz.johnyapps.mutliplatform.library.game.data.model.GameNotFoundException
import cz.johnyapps.mutliplatform.library.game.domain.model.GameBoardFlow
import cz.johnyapps.mutliplatform.library.game.domain.model.GameId
import cz.johnyapps.tictactoe.multiplaform.core.common.util.model.DataResult

interface GetGameBoardFlowUseCase {

    operator fun invoke(gameId: GameId): DataResult<GameBoardFlow>
}

internal class LiveGetGameBoardFlowUseCase(
    private val gameSource: GameSource,
) : GetGameBoardFlowUseCase {

    override fun invoke(gameId: GameId): DataResult<GameBoardFlow> {
        return try {
            val flow = gameSource.getBoardFlow(gameId)
            DataResult.success(GameBoardFlow(flow))
        } catch (e: GameNotFoundException) {
            DataResult.error(e)
        }
    }
}
