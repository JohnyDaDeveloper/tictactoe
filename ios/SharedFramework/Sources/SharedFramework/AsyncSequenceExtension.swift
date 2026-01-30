extension AsyncSequence {
    
    func mapToSwift<T, SwiftModel>(
        _ transform: @escaping @Sendable (T) -> SwiftModel
    ) -> AsyncCompactMapSequence<Self, SwiftModel> where Element == T? {
        self.compactMap { $0.map(transform) }
    }
}
