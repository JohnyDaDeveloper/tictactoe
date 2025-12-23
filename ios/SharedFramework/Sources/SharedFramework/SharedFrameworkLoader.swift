import FactoryKit
import IosModel

public class SharedFrameworkLoader {
    
    public static func load() {
        
        Container.shared.createGameUC.register {
            LiveCreateGameUC()
        }
    }
}
