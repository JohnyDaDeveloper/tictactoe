import FactoryKit
import Shared

extension Container {
    
    var createGameUseCase: Factory<CreateGameUseCase> {
        Factory(self) { DiWrapper.getting(CreateGameUseCase.self) }
    }
    
    var getGameBoardFlowUseCase: Factory<GetGameBoardFlowUseCase> {
        Factory(self) { DiWrapper.getting(GetGameBoardFlowUseCase.self) }
    }
    
    var dataResultMapper: Factory<DataResultMapper> {
        Factory(self) { LiveDataResultMapper() }
    }
}
