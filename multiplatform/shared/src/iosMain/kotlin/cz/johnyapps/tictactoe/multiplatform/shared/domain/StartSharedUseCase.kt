package cz.johnyapps.tictactoe.multiplatform.shared.domain

import cz.johnyapps.tictactoe.multiplatform.core.logger.util.Log
import cz.johnyapps.tictactoe.multiplatform.core.logger.util.LogSetup
import cz.johnyapps.tictactoe.multiplatform.shared.di.sharedModule
import org.koin.core.context.startKoin
import org.koin.dsl.module

interface StartSharedUseCase {

    fun invoke(dependencyProvider: DependencyProvider)
}

internal class IosStartSharedUseCase : StartSharedUseCase {

    override fun invoke(dependencyProvider: DependencyProvider) {
        LogSetup.setup(dependencyProvider.provideLogWriters())

        startKoin {
            logger(KoinLogger())
            modules(
                sharedModule,
                module {

                }
            )
        }

        Log.debug(TAG) { "Shared setup finished" }
    }

    companion object {
        private const val TAG = "StartSharedUseCase"
    }
}
