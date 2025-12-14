package cz.johnyapps.tictactoe.multiplaform.core.common.di

import cz.johnyapps.tictactoe.multiplaform.core.common.presentation.LatestExecutorFactory
import cz.johnyapps.tictactoe.multiplaform.core.common.presentation.LiveFirstExecutorFactory
import cz.johnyapps.tictactoe.multiplaform.core.common.presentation.LiveLatestExecutorFactory
import cz.johnyapps.tictactoe.multiplaform.core.common.util.CurrentInstantProvider
import cz.johnyapps.tictactoe.multiplaform.core.common.util.LiveCurrentInstantProvider
import cz.johnyapps.tictactoe.multiplaform.core.common.util.LiveTimeZoneProvider
import cz.johnyapps.tictactoe.multiplaform.core.common.util.TimeZoneProvider
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module

val commonMultiplatformCoreModule = module {
    factoryOf(::LiveTimeZoneProvider) bind TimeZoneProvider::class
    factoryOf(::LiveCurrentInstantProvider) bind CurrentInstantProvider::class
    factoryOf(::LiveLatestExecutorFactory) bind LatestExecutorFactory::class
    factoryOf(::LiveFirstExecutorFactory) bind cz.johnyapps.tictactoe.multiplaform.core.common.presentation.FirstExecutorFactory::class

    includes(cz.johnyapps.tictactoe.multiplaform.core.common.di.platformCommonMultiplatformCoreModule)
}
