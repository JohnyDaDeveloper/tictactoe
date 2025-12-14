package cz.johnyapps.tictactoe.multiplatform.core.logger.util

import android.util.Log
import cz.johnyapps.tictactoe.multiplatform.core.logger.util.model.LogSeverity
import kotlin.math.min

class LogcatLogWriter : LogWriter {

    override fun log(
        severity: LogSeverity,
        tag: String,
        throwable: Throwable?,
        messageBlock: () -> String
    ) {
        val message = messageBlock().lines()
            .joinToString(separator = "\n") { line ->
                if (line.length > MAX_LINE_LENGTH) {
                    var currentIndex = 0
                    buildString {
                        while (currentIndex < line.length) {
                            val endIndex = min(currentIndex + MAX_LINE_LENGTH, line.length)

                            append(line.substring(currentIndex, endIndex))
                            appendLine()
                            currentIndex = endIndex
                        }
                    }
                } else {
                    line
                }
            }

        when (severity) {
            LogSeverity.Verbose -> Log.v(tag, message, throwable)
            LogSeverity.Debug -> Log.d(tag, message, throwable)
            LogSeverity.Info -> Log.i(tag, message, throwable)
            LogSeverity.Warn -> Log.w(tag, message, throwable)
            LogSeverity.Error -> Log.e(tag, message, throwable)
        }
    }

    companion object {
        private const val MAX_LINE_LENGTH = 1024
    }
}
