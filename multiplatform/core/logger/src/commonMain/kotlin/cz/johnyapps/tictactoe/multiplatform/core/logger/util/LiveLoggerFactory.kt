package cz.johnyapps.tictactoe.multiplatform.core.logger.util

object LiveLoggerFactory {

    fun create(
        logWriters: List<LogWriter>,
    ): Logger {
        return LiveLogger(logWriters)
    }
}
