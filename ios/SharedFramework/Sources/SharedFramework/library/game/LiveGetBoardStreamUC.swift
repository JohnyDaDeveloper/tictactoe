import IosModel
@preconcurrency import Shared

class LiveGetBoardStreamUC : GetBoardStreamUC {
    
    private let getGameBoardFlowUseCase: Shared.GetGameBoardFlowUseCase
    private let gameBoardMapper: GameBoardMapper
    private let gameIdMapper: GameIdMapper
    private let dataResultMapper: DataResultMapper
    
    init(
        getGameBoardFlowUseCase: Shared.GetGameBoardFlowUseCase,
        gameBoardMapper: GameBoardMapper,
        gameIdMapper: GameIdMapper,
        dataResultMapper: DataResultMapper,
    ) {
        self.getGameBoardFlowUseCase = getGameBoardFlowUseCase
        self.gameBoardMapper = gameBoardMapper
        self.gameIdMapper = gameIdMapper
        self.dataResultMapper = dataResultMapper
    }

    func invoke(gameId: IosModel.GameId) -> any AsyncSequence<IosModel.GameBoard, Never> {
        let gameIdMapper = self.gameIdMapper
        let getGameBoardFlowUseCase = self.getGameBoardFlowUseCase
        let dataResultMapper = self.dataResultMapper
        let gameBoardMapper = self.gameBoardMapper
        
        let sharedResult = getGameBoardFlowUseCase.invoke(gameId: gameIdMapper.map(gameId))
        let flow: GameBoardFlow
        
        switch onEnum(of: sharedResult) {
        case .success(let result): flow = result.data.unsafelyUnwrapped
        case .error(_): fatalError("Not handeled")
        }
        
        return flow.flow.map({ board in gameBoardMapper.map(board) })
        
        //return dataResultMapper.map(kmpResult: sharedResult, { stream in
        //    mapStream(flow: stream)
        //})
    }
    
    private func mapStream(flow: GameBoardFlow) -> AsyncStream<IosModel.GameBoard> {
        let gameBoardMapper = self.gameBoardMapper
        
        let mappedStream = flow.flow.map { gameBoard in
            gameBoardMapper.map(gameBoard)
        }
        
        return AsyncStream<IosModel.GameBoard> { continuation in
            let task = Task {
                for await element in mappedStream {
                    continuation.yield(element)
                }
                
                continuation.finish()
            }
            
            continuation.onTermination = { _ in task.cancel() }
        }
    }
}
