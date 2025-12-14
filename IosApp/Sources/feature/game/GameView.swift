import SwiftUI
import FactoryKit

struct GameBoardView: View {
    @State private var viewModel = GameViewModel()
    
    var body: some View {
        Text("Game ID: \(viewModel.boardState)")
    }
}

#Preview("Success State") {
    Container.shared.createGameUC.register {
        MockCreateGameUC()
    }
    
    return GameBoardView()
}
