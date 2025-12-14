package cz.johnyapps.tictactoe.multiplaform.core.common.di

import cz.johnyapps.tictactoe.multiplaform.core.common.util.BuildConfigurationProvider
import cz.johnyapps.tictactoe.multiplaform.core.common.util.IosBuildConfigurationProvider
import cz.johnyapps.tictactoe.multiplaform.core.common.util.IosLocaleProvider
import cz.johnyapps.tictactoe.multiplaform.core.common.util.LocaleProvider
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module

actual val platformCommonMultiplatformCoreModule = module {
    factoryOf(::IosLocaleProvider) bind LocaleProvider::class
    factoryOf(::IosBuildConfigurationProvider) bind BuildConfigurationProvider::class
}
