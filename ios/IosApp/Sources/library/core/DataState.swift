import IosModel

public enum DataState<T: Sendable> : Sendable {
    case loading
    
    case ready(T)

    case error(Swift.Error)
}

extension DataState {
    
    func map<R>(
        _ transform: (T) -> R
    ) -> DataState<R> {
        switch self {
        case .loading:
            return .loading
        case .ready(let value):
            return .ready(transform(value))
        case .error(let error):
            return .error(error)
        }
    }
}
