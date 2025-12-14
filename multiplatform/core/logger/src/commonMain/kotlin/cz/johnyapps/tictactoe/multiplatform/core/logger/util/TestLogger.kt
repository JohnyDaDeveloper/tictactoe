package cz.johnyapps.tictactoe.multiplatform.core.logger.util

class TestLogger : Logger {
    override fun verbose(tag: String, messageBlock: () -> String) {
        println("V ($tag) ${messageBlock()}")
    }

    override fun debug(tag: String, messageBlock: () -> String) {
        println("D ($tag) ${messageBlock()}")
    }

    override fun debug(tag: String, throwable: Throwable, messageBlock: () -> String) {
        println("D ($tag) ${messageBlock()}: ${throwable.stackTraceToString()}")
    }

    override fun info(tag: String, messageBlock: () -> String) {
        println("I ($tag) ${messageBlock()}")
    }

    override fun info(tag: String, throwable: Throwable, messageBlock: () -> String) {
        println("I ($tag) ${messageBlock()}: ${throwable.stackTraceToString()}")
    }

    override fun warn(tag: String, messageBlock: () -> String) {
        println("W ($tag) ${messageBlock()}")
    }

    override fun warn(tag: String, throwable: Throwable, messageBlock: () -> String) {
        println("W ($tag) ${messageBlock()}: ${throwable.stackTraceToString()}")
    }

    override fun error(tag: String, messageBlock: () -> String) {
        println("E ($tag) ${messageBlock()}")
    }

    override fun error(tag: String, throwable: Throwable, messageBlock: () -> String) {
        println("E ($tag) ${messageBlock()}: ${throwable.stackTraceToString()}")
    }
}
