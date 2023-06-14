//
//  AddBudgetPopover.swift
//  iosApp
//
//  Created by Rafael Oliveira on 13/06/2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Combine
import shared
import SwiftUI

struct AddBudgetPopover: View {
    var onAddBudget: () -> Void
    var onChooseBudgetGroup: (BudgetGroup) -> Void

    @Binding var name: String
    @Binding var group: BudgetGroup
    @Binding var money: Double

    let formatter: NumberFormatter = {
        let formatter = NumberFormatter()
        formatter.numberStyle = .currency
        formatter.maximumFractionDigits = 2
        formatter.locale = Locale(identifier: "pt_PT")
        return formatter
    }()

    var body: some View {
        ZStack {
            VStack {
                VStack {
                    Text("ADD BUDGET")
                        .font(.title3)
                        .padding(.bottom, 12)

                    TextField("", value: $money, formatter: formatter)
                        .font(.system(size: 50, weight: .bold))
                        .padding(.bottom, 16)
                        .keyboardType(.numbersAndPunctuation)
                        .multilineTextAlignment(.center)
                        .fixedSize()
                }
                Divider()
                    .background(Color.background)
                VStack {
                    HStack {
                        Text("Group: ")
                        BudgetGroupDropDown(group: group, selectGroup: onChooseBudgetGroup)
                    }
                    .frame(maxWidth: .infinity, alignment: .leading)
                    HStack {
                        Text("Name: ")
                        VStack {
                            TextField("Name", text: $name)
                            Divider()
                                .frame(height: 1)
                                .background(Color.background)
                        }
                    }
                    .frame(maxWidth: .infinity, alignment: .leading)
                    .padding(.top, 16)
                }
                .padding(.top, 16)
                Spacer()
                Button(action: onAddBudget) {
                    Text("SAVE")
                        .foregroundColor(Color.surface)
                        .font(.system(size: 18, weight: .bold))
                        .frame(maxWidth: .infinity, maxHeight: 50)
                        .background(
                            RoundedRectangle(cornerRadius: 25)
                        )
                }
            }
            .foregroundColor(Color.onSurface)
            .padding([.top, .bottom], 24)
            .padding([.leading, .trailing], 24)
            .frame(maxWidth: .infinity)
        }
    }
}

/* struct AddBudgetPopover_Previews: PreviewProvider {
     static var previews: some View {
         AddBudgetPopover(onAddBudget: {})
     }
 } */
