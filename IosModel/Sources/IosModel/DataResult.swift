public enum DataResult<T: Sendable> : Sendable {
    case success(T)

    case error(Swift.Error)
}
