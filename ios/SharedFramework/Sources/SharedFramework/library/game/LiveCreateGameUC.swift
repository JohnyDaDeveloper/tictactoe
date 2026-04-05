@preconcurrency import Shared
import IosModel

struct LiveCreateGameUC : IosModel.CreateGameUC {
    
    private let createGameUseCase: CreateGameUseCase
    private let dataResultMapper: DataResultMapper
    
    init(createGameUseCase: CreateGameUseCase, dataResultMapper: DataResultMapper) {
        self.createGameUseCase = createGameUseCase
        self.dataResultMapper = dataResultMapper
    }
    
    func invoke() async -> IosModel.DataResult<IosModel.GameId> {
        let result = try! await createGameUseCase.invoke()
        let mappedResult = dataResultMapper.mapResult(kmpResult: result) { (gameId: Shared.GameId) in
            IosModel.GameId(value: gameId.gameId)
        }
        
        return mappedResult
    }
}
