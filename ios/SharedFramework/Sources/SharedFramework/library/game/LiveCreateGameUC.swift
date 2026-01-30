@preconcurrency import Shared
import FactoryKit
import IosModel

struct LiveCreateGameUC : IosModel.CreateGameUC {
    
    @Injected(\.createGameUseCase) var createGameUseCase
    @Injected(\.dataResultMapper) var dataResultMapper
    
    func invoke() async -> IosModel.DataResult<IosModel.GameId> {
        let result = try! await createGameUseCase.invoke()
        let mappedResult = dataResultMapper.map(kmpResult: result) { (gameId: Shared.GameId) in
            IosModel.GameId(value: gameId.gameId)
        }
        
        return mappedResult
    }
}
