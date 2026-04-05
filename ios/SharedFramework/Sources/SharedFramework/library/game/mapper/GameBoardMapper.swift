import Shared
import IosModel
import FactoryKit

protocol GameBoardMapper : Sendable {
    
    func map(_ sharedModel: Shared.GameBoard) -> IosModel.GameBoard
}

struct LiveGameBoardMapper : GameBoardMapper {
    
    private let playerIdMapper: PlayerIdMapper
    
    init (
        playerIdMapper: PlayerIdMapper,
    ) {
        self.playerIdMapper = playerIdMapper
    }
    
    func map(_ sharedModel: Shared.GameBoard) -> IosModel.GameBoard {
        IosModel.GameBoard(
            moves: sharedModel.moves.map(mapMove),
            size: IosModel.GameBoard.Size(
                columns: sharedModel.size.columns.int,
                rows: sharedModel.size.rows.int
            )
        )
    }
    
    private func mapMove(sharedModel: Shared.GameBoard.Move) -> IosModel.GameBoard.Move {
        IosModel.GameBoard.Move(
            column: sharedModel.column.int,
            row: sharedModel.row.int,
            playerId: playerIdMapper.map(sharedModel: sharedModel.playerId)
        )
    }
}

extension Container {
    
    var gameBoardMapper: Factory<GameBoardMapper> {
        self {
            LiveGameBoardMapper(
                playerIdMapper: self.playerIdMapper()
            )
        }
    }
}
