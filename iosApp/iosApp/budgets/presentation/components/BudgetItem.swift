//
//  BudgetItem.swift
//  iosApp
//
//  Created by Rafael Oliveira on 07/06/2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import shared
import SwiftUI

struct BudgetItem: View {
    var budget: UiBudget

    var body: some View {
        // TODO: Implement budget screen
        NavigationLink(destination: Text("Opened " + String(budget.id))) {
            HStack {
                Text(budget.name)
                Spacer()
                Text(budget.totalMoney.description)
            }
            .padding([.top, .bottom], 12)
            .foregroundColor(Color.onSurface)
        }
    }
}

struct BudgetItem_Previews: PreviewProvider {
    static var previews: some View {
        BudgetItem(
            budget: UiBudget(id: 0, name: "New Budget", totalMoney: 420.0)
        )
    }
}
