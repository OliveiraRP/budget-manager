//
//  BudgetGroupDropDown.swift
//  iosApp
//
//  Created by Rafael Oliveira on 15/06/2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import shared
import SwiftUI

struct BudgetGroupDropDown: View {
    var group: BudgetGroup
    var selectGroup: (BudgetGroup) -> Void

    var body: some View {
        Menu {
            VStack {
                ForEach(BudgetGroup.Companion().allBudgetGroups, id: \.self.id) { group in
                    BudgetGroupDropDownItem(
                        group: group,
                        onClick: {
                            selectGroup(group)
                        }
                    )
                }
            }
        } label: {
            Text(group.groupName)
            Image(systemName: "chevron.down")
        }
    }
}
