import FactoryKit
import IosModel
import OSLog

@MainActor
protocol MakeMoveDelegate {

    func handle(
        stateContainer: GameStateContainer,
        row: Int,
        column: Int,
    ) async
}

@MainActor
struct LiveMakeMoveDelegate : MakeMoveDelegate {
    
    private let makeMoveUC: MakeMoveUC
    
    private let logger = Logger(
        subsystem: "cz.johnyapps.tictactoe",
        category: "MakeMoveDelegate",
    )
    
    init(makeMoveUC: MakeMoveUC) {
        self.makeMoveUC = makeMoveUC
    }

    func handle(
        stateContainer: any GameStateContainer,
        row: Int,
        column: Int,
    ) async {
        let makeMoveUC = makeMoveUC
        
        let state = stateContainer.viewModelState.board
        guard case .ready(let board) = state else {
            logger.warning("Expected .ready state, but was \(String(describing: state))")
            return
        }
        
        let makeMove = MakeMove(
            gameId: board.gameId,
            field: MakeMove.Field(
                column: column,
                row: row,
            )
        )
        
        let result = await makeMoveUC.invoke(makeMove: makeMove)
        if case let .error(error) = result {
            logger.error("Failed to make move: \(error)")
        }
    }
}

extension Container {
    var makeMoveDelegate: Factory<MakeMoveDelegate> {
        self { @MainActor in
            LiveMakeMoveDelegate(
                makeMoveUC: self.makeMoveUC()
            )
        }
    }
}
