//
//  BudgetGroupDropDownItem.swift
//  iosApp
//
//  Created by Rafael Oliveira on 15/06/2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import shared
import SwiftUI

struct BudgetGroupDropDownItem: View {
    var group: BudgetGroup
    var onClick: () -> Void

    var body: some View {
        Button(action: onClick) {
            Text(group.groupName)
                .foregroundColor(Color.onSurface)
        }
    }
}
