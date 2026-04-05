struct BoardUiState {
    var fields: [[FieldUiState]]
}

struct FieldUiState {
    let player: PlayerUiState?
}

struct PlayerUiState {
    let playerId: String
    let icon: IconUiState
}

enum IconUiState {
    case cross
    case circle
}
