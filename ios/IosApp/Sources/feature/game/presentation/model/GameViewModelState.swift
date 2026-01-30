import IosModel

struct GameViewModelState : Copyable {
    var board: DataState<BoardViewModelState>
    
    static let initial = GameViewModelState(
        board: DataState.loading
    )
}
