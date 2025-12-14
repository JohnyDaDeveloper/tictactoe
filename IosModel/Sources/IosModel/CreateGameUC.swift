import FactoryKit

public protocol CreateGameUC {
    
    func invoke() async throws -> DataResult<IosModel.GameId>
}

public extension Container {
    var createGameUC: Factory<CreateGameUC> {
        self { fatalError("CreateGameUC implementation not registered! Call SharedFramework.setup()") }
    }
}
