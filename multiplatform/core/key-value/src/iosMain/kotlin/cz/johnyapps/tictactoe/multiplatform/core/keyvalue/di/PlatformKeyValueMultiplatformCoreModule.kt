package cz.johnyapps.tictactoe.multiplatform.core.keyvalue.di

import cz.johnyapps.tictactoe.multiplatform.core.keyvalue.system.createDataStore
import org.koin.dsl.module

internal actual val platformKeyValueMultiplatformCoreModule = module {
    single { createDataStore() }
}
