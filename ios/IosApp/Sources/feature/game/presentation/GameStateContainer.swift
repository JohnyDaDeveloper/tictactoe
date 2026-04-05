@MainActor
protocol GameStateContainer : AnyObject {
    var viewModelState: GameViewModelState { get set }
}
