package cz.johnyapps.tictactoe.multiplatform.core.logger.util

internal object LiveLoggerFactory {

    fun create(
        logWriters: List<LogWriter>,
    ): Logger {
        return LiveLogger(logWriters)
    }
}
