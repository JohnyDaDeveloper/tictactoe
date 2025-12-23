import SwiftUI
import FactoryKit
import IosModel

public struct GameBoardView: View {
    @State private var viewModel: GameViewModel = Container.shared.gameViewModel()
    
    public init() {}
    
    public var body: some View {
        let gameId = viewModel.state.gameId
        
        switch gameId {
        case .loading:
            Text("Loading...")
            
        case .error:
            Text("Error")
            
        case .ready(let id):
            Text("Game ID: \(id.value)")
        }
    }
}

#Preview("Success State") {
    Container.shared.gameViewModel.register {
        MainActor.assumeIsolated {
            MockGameViewmodel(
                state: GameViewModelState(
                    gameId: DataState.loading
                )
            )
        }
    }
    
    return GameBoardView()
}
