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
                x: sharedModel.size.x.int,
                y: sharedModel.size.y.int
            )
        )
    }
    
    private func mapMove(sharedModel: Shared.GameBoard.Move) -> IosModel.GameBoard.Move {
        IosModel.GameBoard.Move(
            x: sharedModel.x.int,
            y: sharedModel.y.int,
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
