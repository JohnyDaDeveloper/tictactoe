package cz.johnyapps.tictactoe.multiplatform.core.logger.util

object LogSetup {

    fun setup(
        logWriters: List<LogWriter>
    ) {
        Log.logger = LiveLoggerFactory.create(logWriters)
    }

    fun setupForTests() {
        Log.logger = TestLogger()
    }
}
