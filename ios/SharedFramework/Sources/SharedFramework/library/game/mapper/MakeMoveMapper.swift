import FactoryKit
import IosModel
import Shared

protocol MakeMoveMapper : Sendable {
    
    func map(_ makeMove: IosModel.MakeMove) -> Shared.MakeMove
}

struct LiveMakeMoveMapper : MakeMoveMapper {
    
    private let gameIdMapper: GameIdMapper
    
    init(gameIdMapper: GameIdMapper) {
        self.gameIdMapper = gameIdMapper
    }
    
    func map(_ makeMove: IosModel.MakeMove) -> Shared.MakeMove {
        return Shared.MakeMove(
            gameId: gameIdMapper.map(makeMove.gameId),
            field: Shared.MakeMove.Field(
                column: Int32(makeMove.field.column),
                row: Int32(makeMove.field.row),
            ),
        )
    }
}

extension Container {
    
    var makeMoveMapper: Factory<MakeMoveMapper> {
        self {
            LiveMakeMoveMapper(
                gameIdMapper: self.gameIdMapper()
            )
        }
    }
}
