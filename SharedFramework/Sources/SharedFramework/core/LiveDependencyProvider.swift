import Shared

class LiveDependencyProvider: DependencyProvider {

    func provideLogWriters() -> [any LogWriter] {
        return [
            IosLogWriter(),
        ]
    }
}
