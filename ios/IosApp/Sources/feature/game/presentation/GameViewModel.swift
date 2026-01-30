import Observation
import FactoryKit
import OSLog
import IosModel

@MainActor
protocol GameViewModel {
    
    var state: GameUiState { get }
    
    func send(action: GameAction)
}

@Observable
class LiveGameViewModel : GameViewModel, GameStateContainer {
    @ObservationIgnored
    private let loadGameDelegate: LoadGameDelegate
    @ObservationIgnored
    private let gameUiStateMapper: GameUiStateMapper
    
    var viewModelState: GameViewModelState = .initial
    var state: GameUiState {
        return gameUiStateMapper.map(viewModelState: viewModelState)
    }

    private let logger = Logger(
        subsystem: "cz.johnyapps.tictactoe",
        category: "GameViewModel",
    )

    init(
        loadGameDelegate: LoadGameDelegate,
        gameUiStateMapper: GameUiStateMapper,
    ) {
        self.loadGameDelegate = loadGameDelegate
        self.gameUiStateMapper = gameUiStateMapper
        
        loadGameDelegate.handle(stateContainer: self)
    }
    
    func send(action: GameAction) {
        switch action {
        case .onFieldClick(row: let row, column: let column):
            break
        }
    }
}

@Observable
class MockGameViewmodel : GameViewModel {
    
    var state: GameUiState
    
    init(state: GameUiState) {
        self.state = state
    }
    
    func send(action: GameAction) {
        // Nothing
    }
}
