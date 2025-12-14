package cz.johnyapps.tictactoe.multiplatform.shared.domain

import cz.johnyapps.tictactoe.multiplatform.core.logger.util.Log
import org.koin.core.logger.Level
import org.koin.core.logger.Logger
import org.koin.core.logger.MESSAGE

class KoinLogger : Logger() {
    override fun display(level: Level, msg: MESSAGE) {
        when (level) {
            Level.DEBUG -> Log.debug(TAG) { msg }
            Level.INFO,
            Level.NONE -> Log.info(TAG) { msg }
            Level.WARNING -> Log.warn(TAG) { msg }
            Level.ERROR -> Log.error(TAG) { msg }
        }
    }

    companion object {
        private const val TAG = "KoinLogger"
    }
}
