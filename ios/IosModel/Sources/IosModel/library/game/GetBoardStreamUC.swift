import FactoryKit

public struct SequenceHolder<T: Sendable, Sequence : AsyncSequence<T, Never>> : Sendable where Sequence: Sendable {
    
    public let sequence: Sequence
}

public protocol GetBoardStreamUC: Sendable {
    
    func invoke(gameId: GameId) -> DataResult<any AsyncSequence<IosModel.GameBoard, Never>>
}

public extension Container {
    var getBoardStreamUC: Factory<GetBoardStreamUC> {
        self {
            fatalError("GetBoardStreamUC implementation not registered! Call SharedFramework.setup()")
        }
    }
}
