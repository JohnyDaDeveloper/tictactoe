import Shared
import FactoryKit
import IosModel

public class LiveCreateGameUC : IosModel.CreateGameUC {
    
    @Injected(\.createGameUseCase) var createGameUseCase
    @Injected(\.dataResultMapper) var dataResultMapper
    
    public func invoke() async throws -> IosModel.DataResult<IosModel.GameId> {
        let result = try await createGameUseCase.invoke()
        let mappedResult = dataResultMapper.map(kmpResult: result) { (gameId: Shared.GameId) in
            IosModel.GameId(value: gameId.gameId)
        }
        
        return mappedResult
    }
}
