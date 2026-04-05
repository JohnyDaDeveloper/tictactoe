import FactoryKit

public protocol MakeMoveUC : Sendable {
    
    func invoke(makeMove: MakeMove) async -> DataResult<Void>
}

public extension Container {
    
    var makeMoveUC: Factory<MakeMoveUC> {
        self {
            fatalError("MakeMoveUC implementation not registered! Call SharedFramework.setup()")
        }
    }
}
