//
//  ContentView.swift
//  BoxBox
//
//  Created by Guilherme Rogerio Toquete on 15/05/25.
//

import SwiftUI
import sharedKit

struct ContentView: View {
    var body: some View {
        VStack {
            Image(systemName: "globe")
                .imageScale(.large)
                .foregroundStyle(.tint)
            Text("Hello, " + Platform_iosKt.platform() + "!")
        }
        .padding()
    }
}

#Preview {
    ContentView()
}
