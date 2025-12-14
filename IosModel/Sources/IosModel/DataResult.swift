public enum DataResult<T: Sendable> {
    case success(T)

    case error(Swift.Error)
}
