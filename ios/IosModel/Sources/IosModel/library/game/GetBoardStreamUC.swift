import FactoryKit

public protocol GetBoardStreamUC {
    
    func invoke(gameId: GameId) -> any AsyncSequence<GameBoard?, Error>
}

public extension Container {
    var getBoardStreamUC: Factory<GetBoardStreamUC> {
        self {
            fatalError("GetBoardStreamUC implementation not registered! Call SharedFramework.setup()")
        }
    }
}
