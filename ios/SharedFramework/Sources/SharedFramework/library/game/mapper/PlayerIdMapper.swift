import Shared
import IosModel
import FactoryKit

protocol PlayerIdMapper : Sendable {
    
    func map(sharedModel: Shared.PlayerId) -> IosModel.PlayerId
}

struct LivePlayerIdMapper : PlayerIdMapper {
    
    func map(sharedModel: Shared.PlayerId) -> IosModel.PlayerId {
        IosModel.PlayerId(playerId: sharedModel.playerId)
    }
}

extension Container {
    
    var playerIdMapper: Factory<PlayerIdMapper> {
        self { LivePlayerIdMapper() }
    }
}
