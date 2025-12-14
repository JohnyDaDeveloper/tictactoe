package cz.johnyapps.tictactoe.multiplatform.core.keyvalue.di

import cz.johnyapps.tictactoe.multiplatform.core.keyvalue.data.KeyValueStorage
import cz.johnyapps.tictactoe.multiplatform.core.keyvalue.system.LiveKeyValueStorage
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module

val keyValueMultiplatformCoreModule = module {
    includes(platformKeyValueMultiplatformCoreModule)

    factoryOf(::LiveKeyValueStorage) bind KeyValueStorage::class
}
