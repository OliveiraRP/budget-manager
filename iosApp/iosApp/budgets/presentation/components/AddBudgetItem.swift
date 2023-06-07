//
//  AddBudget.swift
//  iosApp
//
//  Created by Rafael Oliveira on 07/06/2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct AddBudgetItem: View {
    var onClick: () -> Void

    var body: some View {
        Button(action: onClick) {
            HStack {
                Image(systemName: "plus.circle.fill")
                Text("Add new budget")
            }
            Spacer()
        }
        .padding([.top, .bottom], 12)
        .foregroundColor(Color.onSurface)
    }
}

struct AddBudget_Previews: PreviewProvider {
    static var previews: some View {
        AddBudgetItem(onClick: {})
    }
}
