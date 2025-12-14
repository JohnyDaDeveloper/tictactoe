import Observation
import Shared
import Factory
import OSLog

@MainActor
@Observable
class GameViewModel {
    
    var boardState: String = ""
    
    let logger = Logger(
        subsystem: "cz.johnyapps.tictactoe",
        category: "GameViewModel",
    )

    @ObservationIgnored
    @Injected(\.createGameUseCase) private var createGameUseCase
    
    init() {
        Task {
            let gameIdResult = try await createGameUseCase.invoke()
            switch onEnum(of: gameIdResult) {
            case .error(let result):
                logger.error("Failed to create game: \(result.exception.getStacktraceString())")
                break
            case .success(let result):
                logger.info("Created new game with ID \(result.data?.gameId ?? "unknown")")
                break
            }
        }
    }
}
