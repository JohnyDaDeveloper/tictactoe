public enum DataResult<T: Sendable> : Sendable {
    case success(T)

    case error(Swift.Error)
}

extension DataResult {
    
    func map<R: Sendable>(_ block: (T) -> R) -> DataResult<R> {
        switch self {
        case .success(let data):
            return .success(block(data))
        case .error(let error):
            return .error(error)
        }
    }
}
