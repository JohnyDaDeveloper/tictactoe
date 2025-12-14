package cz.johnyapps.mutliplatform.library.game.di

import cz.johnyapps.mutliplatform.library.game.data.GameSource
import cz.johnyapps.mutliplatform.library.game.data.LiveGameSource
import cz.johnyapps.mutliplatform.library.game.domain.CreateGameUseCase
import cz.johnyapps.mutliplatform.library.game.domain.GameCaller
import cz.johnyapps.mutliplatform.library.game.domain.GetGameBoardFlowUseCase
import cz.johnyapps.mutliplatform.library.game.domain.LiveCreateGameUseCase
import cz.johnyapps.mutliplatform.library.game.domain.LiveGameCaller
import cz.johnyapps.mutliplatform.library.game.domain.LiveGetGameBoardFlowUseCase
import cz.johnyapps.mutliplatform.library.game.domain.LiveMakeMoveUseCase
import cz.johnyapps.mutliplatform.library.game.domain.MakeMoveUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val gameMultiplatformLibraryModule = module {
    singleOf(::LiveGameSource) bind GameSource::class

    factoryOf(::LiveGetGameBoardFlowUseCase) bind GetGameBoardFlowUseCase::class
    factoryOf(::LiveCreateGameUseCase) bind CreateGameUseCase::class
    factoryOf(::LiveMakeMoveUseCase) bind MakeMoveUseCase::class

    factoryOf(::LiveGameCaller) bind GameCaller::class
}
