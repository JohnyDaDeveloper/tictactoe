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
                count: gameBoard.size.rows
            ),
            count: gameBoard.size.columns
        )
        
        for move in gameBoard.moves {
            fields[move.column][move.row] = FieldUiState(
                player: PlayerUiState(
                    playerId: move.playerId.playerId,
                    icon: IconUiState.circle,
                )
            )
        }
        
        return fields
    }
}
