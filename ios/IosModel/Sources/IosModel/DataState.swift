public enum DataState<T: Sendable> : Sendable {
    
    case loading
    
    case ready(T)
    
    case error
}
