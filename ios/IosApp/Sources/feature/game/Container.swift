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
        Factory(self) { @MainActor in
            LiveLoadGameDelegate(
                createGameUseCase: self.createGameUC(),
                getBoardStreamUseCase: self.getBoardStreamUC(),
                fieldsMapper: self.fieldsMapper(),
            )
        }
    }

    var gameViewModel: Factory<GameViewModel> {
        Factory(self) { @MainActor in
            LiveGameViewModel(
                loadGameDelegate: self.loadGameDelegate(),
                makeMoveDelegate: self.makeMoveDelegate(),
                gameUiStateMapper: self.gameUiStateMapper(),
            )
        }
    }
    
    var fieldsMapper: Factory<FieldsMapper> {
        Factory(self) {
            LiveFieldsMapper()
        }
    }
}
