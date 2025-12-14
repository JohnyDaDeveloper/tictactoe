//
//  ContentView.swift
//  tictactoe
//
//  Created by Jan Pejsar on 14.12.2025.
//

import SwiftUI

struct ContentView: View {
    var body: some View {
        VStack {
            GameBoardView()
        }
        .padding()
    }
}

#Preview {
    ContentView()
}
