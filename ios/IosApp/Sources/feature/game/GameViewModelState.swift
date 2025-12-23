import IosModel

struct GameViewModelState : Copyable {
    var gameId: DataState<GameId>
    
    static let initial = GameViewModelState(
        gameId: DataState.loading
    )
}
