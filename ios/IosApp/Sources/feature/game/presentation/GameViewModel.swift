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
    @ObservationIgnored
    private var tasks: [Task<Void, Never>] = []
    
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
        
        let task = Task {
            await loadGameDelegate.handle(stateContainer: self)
        }
        tasks.append(task)
    }
    
    func send(action: GameAction) {
        switch action {
        case .onFieldClick(row: let row, column: let column):
            logger.info("Clicked on row:\(row) column\(column)")
            break
        }
    }
    
    deinit {
        for task in tasks {
            task.cancel()
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
