public enum DataResult<T> {
    case success(T)

    case error(Swift.Error)
}
