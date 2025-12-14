package cz.johnyapps.tictactoe.multiplaform.core.common.presentation

import kotlinx.coroutines.CoroutineScope

interface LatestExecutorFactory {

    fun create(coroutineScope: CoroutineScope): LatestExecutor
}

internal class LiveLatestExecutorFactory : LatestExecutorFactory {

    override fun create(coroutineScope: CoroutineScope): LatestExecutor {
        return LiveLatestExecutor(coroutineScope)
    }
}
