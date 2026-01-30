
public struct GameBoard : Sendable {
    public let moves: [Move]
    public let size: Size
    
    public init(moves: [Move], size: Size) {
        self.moves = moves
        self.size = size
    }

    public struct Move: Hashable, Sendable {
        public let x: Int
        public let y: Int
        public let playerId: PlayerId
        
        public init(x: Int, y: Int, playerId: PlayerId) {
            self.x = x
            self.y = y
            self.playerId = playerId
        }
    }

    public struct Size : Hashable, Sendable {
        public let x: Int
        public let y: Int
        
        public init(x: Int, y: Int) {
            self.x = x
            self.y = y
        }
    }
}
