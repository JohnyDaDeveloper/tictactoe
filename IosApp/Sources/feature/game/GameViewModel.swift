import Observation
import FactoryKit
import OSLog
import IosModel

@MainActor
@Observable
class GameViewModel {
    
    var boardState: String = ""
    
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
                boardState = result.value
                break
            }
        }
    }
}
