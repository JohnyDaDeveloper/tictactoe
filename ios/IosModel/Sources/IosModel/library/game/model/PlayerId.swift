
public struct PlayerId: Hashable, Sendable {
    public let playerId: String
    
    public init(playerId: String) {
        self.playerId = playerId
    }
}
