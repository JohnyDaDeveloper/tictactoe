package cz.johnyapps.mutliplatform.library.game.domain

import cz.johnyapps.tictactoe.multiplaform.core.common.util.model.DataResult
import cz.johnyapps.mutliplatform.library.game.data.GameSource
import cz.johnyapps.mutliplatform.library.game.domain.model.GameId

interface CreateGameUseCase {
    
    suspend operator fun invoke(): DataResult<GameId>
}

internal class LiveCreateGameUseCase(
    private val gameSource: GameSource,
    private val gameCaller: GameCaller,
) : CreateGameUseCase {

    override suspend fun invoke(): DataResult<GameId> {
        return gameCaller.callGame {
            gameSource.createGame()
        }
    }
}
