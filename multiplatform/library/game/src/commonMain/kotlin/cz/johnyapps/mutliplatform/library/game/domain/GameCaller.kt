package cz.johnyapps.mutliplatform.library.game.domain

import cz.johnyapps.tictactoe.multiplaform.core.common.util.model.DataResult

internal interface GameCaller {
    
    suspend fun <T> callGame(
        block: suspend () -> T,
    ): DataResult<T>
}

internal class LiveGameCaller : GameCaller {

    override suspend fun <T> callGame(block: suspend () -> T): DataResult<T> {
        return try {
            val data = block()
            DataResult.success(data)
        } catch (e: Exception) {
            DataResult.error(e)
        }
    }
}
