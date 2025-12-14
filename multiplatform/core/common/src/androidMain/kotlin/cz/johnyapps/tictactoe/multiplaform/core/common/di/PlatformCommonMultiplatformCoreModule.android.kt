package cz.johnyapps.tictactoe.multiplaform.core.common.di

import cz.johnyapps.tictactoe.multiplaform.core.common.util.AndroidBuildConfigurationProvider
import cz.johnyapps.tictactoe.multiplaform.core.common.util.AndroidLocaleProvider
import cz.johnyapps.tictactoe.multiplaform.core.common.util.BuildConfigurationProvider
import cz.johnyapps.tictactoe.multiplaform.core.common.util.LocaleProvider
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module

actual val platformCommonMultiplatformCoreModule = module {
    factoryOf(::AndroidLocaleProvider) bind LocaleProvider::class
    factoryOf(::AndroidBuildConfigurationProvider) bind BuildConfigurationProvider::class
}
