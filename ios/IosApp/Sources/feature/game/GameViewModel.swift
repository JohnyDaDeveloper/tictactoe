import Observation
import FactoryKit
import OSLog
import IosModel

@MainActor
protocol GameViewModel {
    
    var state: GameViewModelState { get }
    
    func send(action: GameAction)
}

@Observable
class LiveGameViewModel : GameViewModel {
    
    private var _state: GameViewModelState = .initial
    var state: GameViewModelState {
        return _state
    }
    
    let logger = Logger(
        subsystem: "cz.johnyapps.tictactoe",
        category: "GameViewModel",
    )

    @ObservationIgnored
    @Injected(\.createGameUC) private var createGameUC

    init() {
        Task {
            let gameIdResult = try await createGameUC.invoke()
            switch gameIdResult {
            case .error(let result):
                logger.error("Failed to create game: \(result.localizedDescription)")
                break
            case .success(let result):
                logger.info("Created new game with ID \(result.value)")
                _state.gameId = DataState.ready(result)
                break
            }
        }
    }
    
    func send(action: GameAction) {
        
    }
}

@Observable
class MockGameViewmodel : GameViewModel {
    
    var state: GameViewModelState
    
    init(state: GameViewModelState) {
        self.state = state
    }
    
    func send(action: GameAction) {
        // Nothing
    }
}
