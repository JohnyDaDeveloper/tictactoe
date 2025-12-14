package cz.johnyapps.tictactoe.multiplatform.core.keyvalue.di

import android.content.Context
import cz.johnyapps.tictactoe.multiplatform.core.keyvalue.system.createDataStore
import org.koin.dsl.module

internal actual val platformKeyValueMultiplatformCoreModule = module {
    single {
        val context: Context = get()
        createDataStore(context)
    }
}
