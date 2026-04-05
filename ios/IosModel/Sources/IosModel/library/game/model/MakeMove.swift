public struct MakeMove {
    public let gameId: GameId
    public let field: Field
    
    public init(gameId: GameId, field: Field) {
        self.gameId = gameId
        self.field = field
    }
    
    public struct Field {
        public let column: Int
        public let row: Int
        
        public init(column: Int, row: Int) {
            self.column = column
            self.row = row
        }
    }
}
