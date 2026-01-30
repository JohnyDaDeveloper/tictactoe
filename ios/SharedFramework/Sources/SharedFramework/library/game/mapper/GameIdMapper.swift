import Shared
import IosModel

protocol GameIdMapper : Sendable {
    
    func map(_ iosModel: IosModel.GameId) -> Shared.GameId
}

struct LiveGameIdMapper : GameIdMapper {
    
    func map(_ iosModel: IosModel.GameId) -> Shared.GameId {
        Shared.GameId(gameId: iosModel.value)
    }
}
