package cz.johnyapps.tictactoe.multiplaform.core.common.presentation

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

interface FirstExecutor {

    fun launch(
        block: suspend () -> Unit
    )

    fun cancel()
}

internal class LiveFirstExecutor(
    private val coroutineScope: CoroutineScope,
) : FirstExecutor {

    private var job: Job? = null
    private val jobMutex = Mutex()

    override fun launch(block: suspend () -> Unit) {
        runBlocking {
            jobMutex.withLock {
                val currentJob = job
                if (currentJob == null || currentJob.isCompleted) {
                    job = coroutineScope.launch {
                        block()
                    }
                }
            }
        }
    }

    override fun cancel() {
        job?.cancel()
    }
}
