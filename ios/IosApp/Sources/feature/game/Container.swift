import FactoryKit

extension Container {
    
    var boardUiStateMapper: Factory<BoardUiStateMapper> {
        Factory(self) {
            LiveBoardUiStateMapper()
        }
    }
    
    var gameUiStateMapper: Factory<GameUiStateMapper> {
        Factory(self) {
            LiveGameUiStateMapper(
                boardUiStateMapper: self.boardUiStateMapper()
            )
        }
    }
    
    var loadGameDelegate: Factory<LoadGameDelegate> {
        Factory(self) {
            LiveLoadGameDelegate()
        }
    }

    var gameViewModel: Factory<GameViewModel> {
        Factory(self) { @MainActor in
            LiveGameViewModel(
                loadGameDelegate: self.loadGameDelegate(),
                gameUiStateMapper: self.gameUiStateMapper(),
            )
        }
    }
}
