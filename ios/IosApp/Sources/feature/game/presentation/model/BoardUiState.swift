struct BoardUiState {
    var fields: [[FieldUiState]]
}

struct FieldUiState {
    let player: PlayerUiState?
}

struct PlayerUiState {
    let playerId: Int
    let icon: IconUiState
}

enum IconUiState {
    case cross
    case circle
}
