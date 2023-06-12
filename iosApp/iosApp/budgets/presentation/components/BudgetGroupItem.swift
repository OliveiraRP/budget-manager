//
//  BudgetList.swift
//  iosApp
//
//  Created by Rafael Oliveira on 07/06/2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import shared
import SwiftUI

struct BudgetGroupItem: View {
    var group: UiBudgetGroup
    var onAddBudget: () -> Void

    var body: some View {
        VStack {
            VStack {
                HStack {
                    Text(group.name)
                        .font(.system(size: 28, weight: Font.Weight.bold))
                        .foregroundColor(Color.onSurface)
                        .padding([.top, .bottom], 12)
                    Spacer()
                }
                ForEach(group.budgetList, id: \.self.id) { budget in
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
        BudgetGroupItem(
            group: UiBudgetGroup(
                id: 1,
                name: "Bank",
                budgetList: [UiBudget(
                    id: 1,
                    budgetGroup: BudgetGroup.bank,
                    name: "New Budget",
                    totalMoney: 420.0
                )]
            ),
            onAddBudget: {}
        )
    }
}
