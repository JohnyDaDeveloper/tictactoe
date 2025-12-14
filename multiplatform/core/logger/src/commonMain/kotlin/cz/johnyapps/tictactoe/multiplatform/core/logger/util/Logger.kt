package cz.johnyapps.tictactoe.multiplatform.core.logger.util

interface Logger {

    fun verbose(
        tag: String,
        messageBlock: () -> String,
    )

    fun debug(
        tag: String,
        messageBlock: () -> String,
    )

    fun debug(
        tag: String,
        throwable: Throwable,
        messageBlock: () -> String,
    )

    fun info(
        tag: String,
        messageBlock: () -> String,
    )

    fun info(
        tag: String,
        throwable: Throwable,
        messageBlock: () -> String,
    )

    fun warn(
        tag: String,
        messageBlock: () -> String,
    )

    fun warn(
        tag: String,
        throwable: Throwable,
        messageBlock: () -> String,
    )

    fun error(
        tag: String,
        messageBlock: () -> String,
    )

    fun error(
        tag: String,
        throwable: Throwable,
        messageBlock: () -> String,
    )
}
