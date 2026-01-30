import IosModel
import OSLog

protocol LoadGameDelegate {
    
    @MainActor
    func handle(stateContainer: GameStateContainer) async
}

struct LiveLoadGameDelegate : LoadGameDelegate {
    private let createGameUseCase: CreateGameUC
    private let getBoardStreamUseCase: GetBoardStreamUC
    
    
    private let logger = Logger(
        subsystem: "cz.johnyapps.tictactoe",
        category: "LoadGameDelegate",
    )
    
    
    @MainActor
    func handle(stateContainer: GameStateContainer) async {
        let result = await createGameUseCase.invoke()
        
        switch result {
        case .success(let gameId): collectBoard(gameId: gameId)
            
            
        case .error(let error): logger.error(
            "Failed to create game: \(error)"
        )
        }
    }
    
    private func collectBoard(gameId: GameId) {
        getBoardStreamUseCase.invoke(gameId: gameId)
    }
}
