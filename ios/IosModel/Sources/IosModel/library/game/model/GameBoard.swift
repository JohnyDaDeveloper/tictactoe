public struct GameBoard : Sendable {
    public let moves: [Move]
    public let size: Size
    
    public init(moves: [Move], size: Size) {
        self.moves = moves
        self.size = size
    }

    public struct Move: Hashable, Sendable {
        public let column: Int
        public let row: Int
        public let playerId: PlayerId
        
        public init(column: Int, row: Int, playerId: PlayerId) {
            self.column = column
            self.row = row
            self.playerId = playerId
        }
    }

    public struct Size : Hashable, Sendable {
        public let columns: Int
        public let rows: Int
        
        public init(columns: Int, rows: Int) {
            self.columns = columns
            self.rows = rows
        }
    }
}
