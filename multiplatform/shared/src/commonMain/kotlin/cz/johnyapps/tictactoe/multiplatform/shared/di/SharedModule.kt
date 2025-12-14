package cz.johnyapps.tictactoe.multiplatform.shared.di

import cz.johnyapps.mutliplatform.library.game.di.gameMultiplatformLibraryModule
import cz.johnyapps.tictactoe.multiplaform.core.common.di.commonMultiplatformCoreModule
import org.koin.dsl.module

internal val sharedModule = module {
    includes(
        commonMultiplatformCoreModule,
        gameMultiplatformLibraryModule,
        platformSharedModule,
    )
}
