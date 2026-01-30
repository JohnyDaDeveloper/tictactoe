@MainActor
protocol GameStateContainer {
    var viewModelState: GameViewModelState { get set }
}
