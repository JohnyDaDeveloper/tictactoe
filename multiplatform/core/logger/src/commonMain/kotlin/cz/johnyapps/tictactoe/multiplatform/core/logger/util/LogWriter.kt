package cz.johnyapps.tictactoe.multiplatform.core.logger.util

import cz.johnyapps.tictactoe.multiplatform.core.logger.util.model.LogSeverity

interface LogWriter {

    fun log(
        severity: LogSeverity,
        tag: String,
        throwable: Throwable?,
        messageBlock: () -> String,
    )
}
