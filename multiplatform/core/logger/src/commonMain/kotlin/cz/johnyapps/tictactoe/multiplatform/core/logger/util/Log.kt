package cz.johnyapps.tictactoe.multiplatform.core.logger.util

object Log {

    internal lateinit var logger: Logger

    fun verbose(
        tag: String,
        messageBlock: () -> String,
    ) = logger.verbose(tag, messageBlock)

    fun debug(
        tag: String,
        messageBlock: () -> String,
    ) = logger.debug(tag, messageBlock)

    fun debug(
        tag: String,
        throwable: Throwable,
        messageBlock: () -> String,
    ) = logger.debug(tag, throwable, messageBlock)

    fun info(
        tag: String,
        messageBlock: () -> String,
    ) = logger.info(tag, messageBlock)

    fun info(
        tag: String,
        throwable: Throwable,
        messageBlock: () -> String,
    ) = logger.info(tag, throwable, messageBlock)

    fun warn(
        tag: String,
        messageBlock: () -> String,
    ) = logger.warn(tag, messageBlock)

    fun warn(
        tag: String,
        throwable: Throwable,
        messageBlock: () -> String,
    ) = logger.warn(tag, throwable, messageBlock)

    fun error(
        tag: String,
        messageBlock: () -> String,
    ) = logger.error(tag, messageBlock)

    fun error(
        tag: String,
        throwable: Throwable,
        messageBlock: () -> String,
    ) = logger.error(tag, throwable, messageBlock)
}
