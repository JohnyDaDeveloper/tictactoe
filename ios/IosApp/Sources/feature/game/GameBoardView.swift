import SwiftUI
import FactoryKit
import IosModel

public struct GameBoardView: View {
    @State private var viewModel: GameViewModel = Container.shared.gameViewModel()
    
    public init() {}
    
    public var body: some View {
        let boardData = viewModel.state.board
        
        ZStack {
            switch boardData {
            case .loading:
                Text("Loading...")
                
            case .error:
                Text("Error")
                
            case .ready(let board):
                BoardView(
                    state: board,
                    onFieldClick: { _, _ in },
                )
            }
        }
    }
}

#Preview {
    Container.shared.gameViewModel.register {
        MainActor.assumeIsolated {
            MockGameViewmodel(
                state: GameUiState(
                    board: DataState.ready(
                        BoardUiState(
                            fields: [
                                [FieldUiState(player: nil), FieldUiState(player: nil), FieldUiState(player: nil)],
                                [FieldUiState(player: nil), FieldUiState(player: nil), FieldUiState(player: nil)],
                                [FieldUiState(player: nil), FieldUiState(player: nil), FieldUiState(player: nil)]
                            ]
                        )
                    )
                )
            )
        }
    }
    
    return GameBoardView()
}
