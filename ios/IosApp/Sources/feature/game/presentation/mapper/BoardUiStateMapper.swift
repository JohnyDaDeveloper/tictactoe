protocol BoardUiStateMapper {
    func map(boardViewModelState: BoardViewModelState) -> BoardUiState
}

class LiveBoardUiStateMapper : BoardUiStateMapper {
    func map(boardViewModelState: BoardViewModelState) -> BoardUiState {
        return BoardUiState(
            fields: boardViewModelState.fields,
        )
    }
}
