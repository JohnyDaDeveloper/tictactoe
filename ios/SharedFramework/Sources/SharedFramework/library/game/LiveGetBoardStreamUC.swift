import IosModel
@preconcurrency import Shared

class LiveGetBoardStreamUC : GetBoardStreamUC {
    
    private let getGameBoardFlowUseCase: Shared.GetGameBoardFlowUseCase
    private let gameBoardMapper: GameBoardMapper
    private let gameIdMapper: GameIdMapper
    
    init(
        getGameBoardFlowUseCase: Shared.GetGameBoardFlowUseCase,
        gameBoardMapper: GameBoardMapper,
        gameIdMapper: GameIdMapper,
    ) {
        self.getGameBoardFlowUseCase = getGameBoardFlowUseCase
        self.gameBoardMapper = gameBoardMapper
        self.gameIdMapper = gameIdMapper
    }

    func invoke(gameId: IosModel.GameId) -> any AsyncSequence<IosModel.GameBoard?, Error> {
//        let (stream, continuation) = AsyncSequence.makeStream(of: IosModel.GameBoard?.self)
        let gameIdMapper = self.gameIdMapper
        let gameBoardMapper = self.gameBoardMapper
        let getGameBoardFlowUseCase = self.getGameBoardFlowUseCase
        
        return getGameBoardFlowUseCase.invoke(gameId: gameIdMapper.map(gameId))
            .map({ gameBoard in
                gameBoardMapper.map(gameBoard!)
            })
//            .map { board in
//                gameBoardMapper.map(board)
//            }
        
//        let task = Task {
//            for await board in getGameBoardFlowUseCase.invoke(
//                gameId: gameIdMapper.map(gameId)
//            ) {
//                if let board {
//                    let iosBoard = gameBoardMapper.map(board)
//                    continuation.yield(iosBoard)
//                }
//            }
//        }
//        
//        continuation.onTermination = { _ in
//            task.cancel()
//        }
//        
//        return stream
    }
}
