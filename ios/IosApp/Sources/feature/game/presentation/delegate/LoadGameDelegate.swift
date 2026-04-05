import IosModel
import OSLog

@MainActor
protocol LoadGameDelegate {

    func handle(stateContainer: any GameStateContainer) async
}

@MainActor
struct LiveLoadGameDelegate : LoadGameDelegate {
    private let createGameUseCase: CreateGameUC
    private let getBoardStreamUseCase: GetBoardStreamUC
    private let fieldsMapper: FieldsMapper
    
    init(
        createGameUseCase: CreateGameUC,
        getBoardStreamUseCase: GetBoardStreamUC,
        fieldsMapper: FieldsMapper,
    ) {
        self.createGameUseCase = createGameUseCase
        self.getBoardStreamUseCase = getBoardStreamUseCase
        self.fieldsMapper = fieldsMapper
    }
    
    private let logger = Logger(
        subsystem: "cz.johnyapps.tictactoe",
        category: "LoadGameDelegate",
    )
    
    func handle(stateContainer: GameStateContainer) async {
        let result = await createGameUseCase.invoke()
        
        switch result {
        case .success(let gameId): await collectBoard(
            gameId: gameId,
            stateContainer: stateContainer,
        )

        case .error(let error): logger.error(
            "Failed to create game: \(error)"
        )
        }
    }
    
    private func collectBoard(
        gameId: GameId,
        stateContainer: GameStateContainer,
    ) async {
        let gameBoardStreamResult = getBoardStreamUseCase.invoke(gameId: gameId)
        
        switch gameBoardStreamResult {
        case .success(let gameBoardStream):
            for await gameBoard in gameBoardStream {
                handleGameBoard(
                    gameId: gameId,
                    gameBoard: gameBoard,
                    stateContainer: stateContainer,
                )
            }
        case .error(let error):
            logger.error(
                "Failed to get game board flow for game \(gameId.value): \(error)"
            )
        }
    }
    
    private func handleGameBoard(
        gameId: GameId,
        gameBoard: GameBoard,
        stateContainer: GameStateContainer,
    ) {
        logger.trace("Board: \(String(describing: gameBoard))")
        
        let viewModelState = BoardViewModelState(
            gameId: gameId,
            fields: fieldsMapper.map(gameBoard: gameBoard),
        )
        stateContainer.viewModelState.board = DataState.ready(
            viewModelState
        )
    }
}
