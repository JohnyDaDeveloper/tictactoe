import IosModel

protocol FieldsMapper {
    
    func map(gameBoard: GameBoard) -> [[FieldUiState]]
}

class LiveFieldsMapper : FieldsMapper {

    func map(gameBoard: IosModel.GameBoard) -> [[FieldUiState]] {
        var fields = Array(
            repeating: Array(
                repeating: FieldUiState(
                    player: nil,
                ),
                count: gameBoard.size.y
            ),
            count: gameBoard.size.x
        )
        
        for move in gameBoard.moves {
            fields[move.x][move.y] = FieldUiState(
                player: PlayerUiState(
                    playerId: move.playerId.playerId,
                    icon: IconUiState.circle,
                )
            )
        }
        
        return fields
    }
}
