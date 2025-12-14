package cz.johnyapps.tictactoe.multiplatform.core.logger.util

fun Throwable.getStacktraceString(): String {
    return stackTraceToString()
}
