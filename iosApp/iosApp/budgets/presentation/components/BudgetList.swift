//
//  BudgetList.swift
//  iosApp
//
//  Created by Rafael Oliveira on 07/06/2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import shared
import SwiftUI

struct BudgetList: View {
    var budgetList: [UiBudget]
    var onAddBudget: () -> Void

    var body: some View {
        VStack {
            VStack {
                ForEach(budgetList, id: \.self.id) { budget in
                    BudgetItem(
                        budget: budget
                    )
                    Divider()
                        .overlay(Color.background)
                }
                AddBudgetItem(
                    onClick: {
                        onAddBudget()
                    }
                )
            }
            .padding([.top, .bottom], 12)
            .padding([.leading, .trailing], 18)
        }
        .background(Color.surface)
        .cornerRadius(12)
    }
}

struct BudgetList_Previews: PreviewProvider {
    static var previews: some View {
        BudgetList(
            budgetList: [UiBudget(id: 1, name: "New Budget 1", totalMoney: 420.0),
                         UiBudget(id: 2, name: "New Budget 2", totalMoney: 420.0)],
            onAddBudget: {}
        )
    }
}
