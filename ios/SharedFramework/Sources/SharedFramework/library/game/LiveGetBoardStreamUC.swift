import IosModel
import ConcurrencyExtras
@preconcurrency import Shared

struct LiveGetBoardStreamUC : GetBoardStreamUC {
    
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

    func invoke(gameId: IosModel.GameId) -> IosModel.DataResult<AsyncStream<IosModel.GameBoard>> {
        let gameIdMapper = self.gameIdMapper
        let getGameBoardFlowUseCase = self.getGameBoardFlowUseCase
        let dataResultMapper = self.dataResultMapper
        let gameBoardMapper = self.gameBoardMapper
        
        let sharedResult = getGameBoardFlowUseCase.invoke(gameId: gameIdMapper.map(gameId))
        return dataResultMapper.mapResult(kmpResult: sharedResult) { flow in
            let iosFlow = flow.flow.map { gameBoard in
                gameBoardMapper.map(gameBoard)
            }
            
            return AsyncStream(iosFlow)
        }
    }
}
