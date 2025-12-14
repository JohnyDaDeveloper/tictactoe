package cz.johnyapps.tictactoe.multiplaform.core.common.presentation

import kotlinx.coroutines.CoroutineScope

interface FirstExecutorFactory {

    fun create(coroutineScope: CoroutineScope): cz.johnyapps.tictactoe.multiplaform.core.common.presentation.FirstExecutor
}

internal class LiveFirstExecutorFactory :
    cz.johnyapps.tictactoe.multiplaform.core.common.presentation.FirstExecutorFactory {

    override fun create(coroutineScope: CoroutineScope): cz.johnyapps.tictactoe.multiplaform.core.common.presentation.FirstExecutor {
        return cz.johnyapps.tictactoe.multiplaform.core.common.presentation.LiveFirstExecutor(
            coroutineScope
        )
    }
}
