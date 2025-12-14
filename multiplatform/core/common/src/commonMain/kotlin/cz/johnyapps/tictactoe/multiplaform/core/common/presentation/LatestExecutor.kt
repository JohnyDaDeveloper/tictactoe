package cz.johnyapps.tictactoe.multiplaform.core.common.presentation

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

interface LatestExecutor {

    fun launchLatest(
        block: suspend () -> Unit
    )
}

internal class LiveLatestExecutor(
    private val coroutineScope: CoroutineScope,
) : LatestExecutor {

    private var job: Job? = null
    private val jobMutex = Mutex()

    override fun launchLatest(block: suspend () -> Unit) {
        runBlocking {
            jobMutex.withLock {
                val oldJob = job
                job = coroutineScope.launch {
                    oldJob?.cancelAndJoin()
                    block()
                }
            }
        }
    }
}
