import FactoryKit
import IosModel
import Shared

public class SharedFrameworkLoader {
    
    public static func load() {
        Container.shared.createGameUC.register {
            LiveCreateGameUC(
                createGameUseCase: DiWrapper.getting(CreateGameUseCase.self),
                dataResultMapper: Container.shared.dataResultMapper()
            )
        }
        
        Container.shared.getBoardStreamUC.register {
            LiveGetBoardStreamUC(
                getGameBoardFlowUseCase: DiWrapper.getting(GetGameBoardFlowUseCase.self),
                gameBoardMapper: Container.shared.gameBoardMapper(),
                gameIdMapper: Container.shared.gameIdMapper(),
                dataResultMapper: Container.shared.dataResultMapper()
            )
        }
        
        Container.shared.makeMoveUC.register {
            LiveMakeMoveUC(
                makeMoveUseCase: DiWrapper.getting(MakeMoveUseCase.self),
                makeMoveMapper: Container.shared.makeMoveMapper(),
                dataResultMapper: Container.shared.dataResultMapper(),
            )
        }
    }
}
