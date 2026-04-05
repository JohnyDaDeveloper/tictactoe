import Shared
import IosModel
import FactoryKit

protocol GameIdMapper : Sendable {
    
    func map(_ iosModel: IosModel.GameId) -> Shared.GameId
}

struct LiveGameIdMapper : GameIdMapper {
    
    func map(_ iosModel: IosModel.GameId) -> Shared.GameId {
        Shared.GameId(gameId: iosModel.value)
    }
}

extension Container {
    var gameIdMapper: Factory<GameIdMapper> {
        self { LiveGameIdMapper() }
    }
}
