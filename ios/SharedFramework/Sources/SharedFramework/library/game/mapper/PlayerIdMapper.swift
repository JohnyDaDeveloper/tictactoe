import Shared
import IosModel

protocol PlayerIdMapper : Sendable {
    
    func map(sharedModel: Shared.PlayerId) -> IosModel.PlayerId
}

struct LivePlayerIdMapper : PlayerIdMapper {
    
    func map(sharedModel: Shared.PlayerId) -> IosModel.PlayerId {
        IosModel.PlayerId(playerId: sharedModel.playerId)
    }
}
