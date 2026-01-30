import FactoryKit

public protocol CreateGameUC : Sendable {
    
    func invoke() async -> DataResult<IosModel.GameId>
}

public extension Container {
    var createGameUC: Factory<CreateGameUC> {
        self { fatalError("CreateGameUC implementation not registered! Call SharedFramework.setup()") }
    }
}
