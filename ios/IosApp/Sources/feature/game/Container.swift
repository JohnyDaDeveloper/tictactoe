import FactoryKit

extension Container {

    @MainActor
    var gameViewModel: Factory<GameViewModel> {
        Factory(self) {
            MainActor.assumeIsolated { LiveGameViewModel() }
        }
    }
}
