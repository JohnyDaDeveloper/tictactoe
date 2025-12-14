package cz.johnyapps.tictactoe.multiplatform.shared.domain

import android.app.Application
import cz.johnyapps.tictactoe.multiplatform.core.logger.util.Log
import cz.johnyapps.tictactoe.multiplatform.core.logger.util.LogSetup
import cz.johnyapps.tictactoe.multiplatform.core.logger.util.LogcatLogWriter
import cz.johnyapps.tictactoe.multiplatform.shared.di.sharedModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

interface StartSharedUseCase {

    operator fun invoke(application: Application)
}

internal class AndroidStartSharedUseCase : StartSharedUseCase {
    override fun invoke(application: Application) {
        LogSetup.setup(listOf(LogcatLogWriter()))

        startKoin {
            androidContext(application)
            modules(sharedModule)
        }

        Log.info(TAG) { "Shared setup finished" }
    }

    companion object {
        private const val TAG = "StartSharedUseCase"
    }
}
