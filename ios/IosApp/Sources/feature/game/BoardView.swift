import SwiftUI

struct BoardView: View {
    let state: BoardUiState
    let onFieldClick: (Int, Int) -> Void
    
    var body: some View {
        let rowCount = state.fields.count
        let columnCount = state.fields.first!.count
        
        GeometryReader { geometry in
            ZStack {
                let spacing = 5
                
                let remainingHeight = geometry.size.height - CGFloat(
                    spacing * (rowCount + 1)
                )
                let remainingWidth = geometry.size.width - CGFloat(
                    spacing * (columnCount + 1)
                )
                
                let horizontalSize = remainingHeight / CGFloat(rowCount)
                let verticalSize = remainingWidth / CGFloat(columnCount)
                let fieldSize = min(horizontalSize, verticalSize)
                
                VStack(spacing: CGFloat(spacing)) {
                    ForEach(0..<rowCount, id: \.self) { row in
                        HStack(spacing: CGFloat(spacing)) {
                            ForEach(0..<columnCount, id: \.self) { column in
                                FieldView(
                                    state: state.fields[row][column],
                                    size: fieldSize,
                                )
                                .onTapGesture {
                                    onFieldClick(row, column)
                                }
                            }
                        }
                    }
                }
                .padding(CGFloat(spacing))
            }
            .frame(maxWidth: .infinity, maxHeight: .infinity)
        }
    }
}

struct FieldView: View {
    let state: FieldUiState
    let size: CGFloat
    
    var body: some View {
        ZStack {
            RoundedRectangle(cornerRadius: 8)
                .fill(Color(.systemGray6))
            
            if let player = state.player {
                IconView(icon: player.icon)
                    .padding(size / 7)
            }
        }
        .frame(width: size, height: size)
    }
}

struct IconView: View {
    let icon: IconUiState
    
    var body: some View {
        switch icon {
        case .cross:
            CrossIcon()
        case .circle:
            CircleIcon()
        }
    }
}

struct CrossIcon: View {
    var body: some View {
        ZStack {
            Rectangle()
                .fill(Color.blue)
                .frame(width: 4)
                .rotationEffect(.degrees(45))
            
            Rectangle()
                .fill(Color.blue)
                .frame(width: 4)
                .rotationEffect(.degrees(-45))
        }
    }
}

struct CircleIcon: View {
    var body: some View {
        Circle()
            .stroke(Color.red, lineWidth: 4)
    }
}

#Preview("Horizontal") {
    BoardView(
        state: BoardUiState(
            fields: generatePreviewFields(
                rowCount: 4,
                columnCount: 3
            )
        ),
        onFieldClick: { _, _ in },
    )
}

#Preview("Vertical") {
    BoardView(
        state: BoardUiState(
            fields: generatePreviewFields(
                rowCount: 15,
                columnCount: 4
            )
        ),
        onFieldClick: { _, _ in },
    )
}

private func generatePreviewFields(
    rowCount: Int,
    columnCount: Int,
) -> [[FieldUiState]] {
    (0...rowCount).map { row in
        (0...columnCount).map { column in
            let index = (row + column)
            let player: PlayerUiState?
            
            switch true {
                case ((index % 3) == 0): player = PlayerUiState(playerId: 0, icon: .cross)
                case ((index % 5) == 0): player = PlayerUiState(playerId: 0, icon: .circle)
                
                default: player = nil
            }
            
            return FieldUiState(player: player)
        }
    }
}

/*
 public var body: some View {
         GeometryReader { geometry in
             let rowCount = board.fields.count
             let columnCount = board.fields.first?.count ?? 0
             let gridSize = max(rowCount, columnCount)
             
             let size = min(geometry.size.width, geometry.size.height)
             let fieldSize = (size - spacing * CGFloat(gridSize - 1)) / CGFloat(gridSize)
             
             VStack(spacing: spacing) {
                 ForEach(0..<rowCount, id: \.self) { row in
                     HStack(spacing: spacing) {
                         ForEach(0..<board.fields[row].count, id: \.self) { column in
                             FieldView(
                                 field: board.fields[row][column],
                                 size: fieldSize
                             )
                             .onTapGesture {
                                 onFieldTap(row, column)
                             }
                         }
                     }
                 }
             }
             .frame(width: size, height: size)
             .frame(maxWidth: .infinity, maxHeight: .infinity)
         }
         .aspectRatio(1, contentMode: .fit)
     }
 }
 */
