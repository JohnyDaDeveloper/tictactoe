package cz.johnyapps.tictactoe.multiplatform.core.logger.util

import cz.johnyapps.tictactoe.multiplatform.core.logger.util.model.LogSeverity

internal class LiveLogger(
    private val logWriters: List<LogWriter>,
) : Logger {
    override fun verbose(tag: String, messageBlock: () -> String) {
        logWriters.forEach { logWriter ->
            logWriter.log(
                severity = LogSeverity.Verbose,
                tag = tag,
                throwable = null,
                messageBlock = messageBlock
            )
        }
    }

    override fun debug(tag: String, messageBlock: () -> String) {
        logWriters.forEach { logWriter ->
            logWriter.log(
                severity = LogSeverity.Debug,
                tag = tag,
                throwable = null,
                messageBlock = messageBlock
            )
        }
    }

    override fun debug(tag: String, throwable: Throwable, messageBlock: () -> String) {
        logWriters.forEach { logWriter ->
            logWriter.log(
                severity = LogSeverity.Debug,
                tag = tag,
                throwable = throwable,
                messageBlock = messageBlock
            )
        }
    }

    override fun info(tag: String, messageBlock: () -> String) {
        logWriters.forEach { logWriter ->
            logWriter.log(
                severity = LogSeverity.Info,
                tag = tag,
                throwable = null,
                messageBlock = messageBlock
            )
        }
    }

    override fun info(tag: String, throwable: Throwable, messageBlock: () -> String) {
        logWriters.forEach { logWriter ->
            logWriter.log(
                severity = LogSeverity.Info,
                tag = tag,
                throwable = throwable,
                messageBlock = messageBlock
            )
        }
    }

    override fun warn(tag: String, messageBlock: () -> String) {
        logWriters.forEach { logWriter ->
            logWriter.log(
                severity = LogSeverity.Warn,
                tag = tag,
                throwable = null,
                messageBlock = messageBlock
            )
        }
    }

    override fun warn(tag: String, throwable: Throwable, messageBlock: () -> String) {
        logWriters.forEach { logWriter ->
            logWriter.log(
                severity = LogSeverity.Warn,
                tag = tag,
                throwable = throwable,
                messageBlock = messageBlock
            )
        }
    }

    override fun error(tag: String, messageBlock: () -> String) {
        logWriters.forEach { logWriter ->
            logWriter.log(
                severity = LogSeverity.Error,
                tag = tag,
                throwable = null,
                messageBlock = messageBlock
            )
        }
    }

    override fun error(tag: String, throwable: Throwable, messageBlock: () -> String) {
        logWriters.forEach { logWriter ->
            logWriter.log(
                severity = LogSeverity.Error,
                tag = tag,
                throwable = throwable,
                messageBlock = messageBlock
            )
        }
    }
}
