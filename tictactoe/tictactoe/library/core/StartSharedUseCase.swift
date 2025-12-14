import Shared

public class StartSharedUseCase {
    
    public init() {}
    
    public func invoke() {
        StartSharedUseCaseProvider.shared
            .provide()
            .invoke(
                dependencyProvider: LiveDependencyProvider()
            )
    }
}
