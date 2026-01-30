protocol GameUiStateMapper {
    func map(viewModelState: GameViewModelState) -> GameUiState
}

class LiveGameUiStateMapper : GameUiStateMapper {
    let boardUiStateMapper: BoardUiStateMapper
    
    init(boardUiStateMapper: BoardUiStateMapper) {
        self.boardUiStateMapper = boardUiStateMapper
    }
    
    func map(viewModelState: GameViewModelState) -> GameUiState {
        GameUiState(
            board: viewModelState.board.map(boardUiStateMapper.map),
        )
    }
}
