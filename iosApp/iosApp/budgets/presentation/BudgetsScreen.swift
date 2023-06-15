//
//  BudgetsScreen.swift
//  iosApp
//
//  Created by Rafael Oliveira on 07/06/2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import shared
import SwiftUI

struct BudgetsScreen: View {
    private var budgetDataSource: BudgetDataSource
    private var getBudgetsUseCase: GetBudgets
    private var addBudgetUseCase: AddBudget
    @ObservedObject var viewModel: IOSBudgetsViewModel

    @State private var showAddBudgetPopover = false

    init(budgetDataSource: BudgetDataSource,
         getBudgetsUseCase: GetBudgets,
         addBudgetUseCase: AddBudget)
    {
        self.budgetDataSource = budgetDataSource
        self.getBudgetsUseCase = getBudgetsUseCase
        self.addBudgetUseCase = addBudgetUseCase
        viewModel = IOSBudgetsViewModel(
            budgetDatasource: budgetDataSource,
            getBudgetsUseCase: getBudgetsUseCase,
            addBudgetUseCase: addBudgetUseCase
        )
    }

    var body: some View {
        ZStack {
            VStack {
                HStack {
                    Image(systemName: "slider.horizontal.3")
                        .frame(maxWidth: .infinity, alignment: .leading)
                        .foregroundColor(Color.onSurface)
                    Text("BUDGETS")
                        .font(.title3)
                        .foregroundColor(Color.onSurface)
                        .frame(maxWidth: .infinity, alignment: .center)
                    Button(action: { showAddBudgetPopover = true }) {
                        Image(systemName: "square.and.pencil")
                            .foregroundColor(Color.onSurface)
                    }
                    .frame(maxWidth: .infinity, alignment: .trailing)
                    .popover(isPresented: $showAddBudgetPopover) {
                        AddBudgetPopover(
                            onAddBudget: { viewModel.onEvent(event: BudgetsEvent.AddBudget());
                                showAddBudgetPopover = false },
                            onChooseBudgetGroup: { group in viewModel.onEvent(event: BudgetsEvent.AddBudgetChangeGroup(group: group)) },
                            name: Binding(get: { viewModel.state.addBudgetName }, set: { value in
                                viewModel.onEvent(event: BudgetsEvent.AddBudgetChangeName(name: value))
                            }),
                            group: Binding(get: { viewModel.state.addBudgetGroup }, set: { value in
                                viewModel.onEvent(event: BudgetsEvent.AddBudgetChangeGroup(group: value))
                            }),
                            money: Binding(get: { viewModel.state.addBudgetTotalMoney }, set: { value in
                                viewModel.onEvent(event: BudgetsEvent.AddBudgetChangeTotalMoney(totalMoney: value))
                            })
                        )
                        .background(Color.surface)
                    }
                }
                .padding([.leading, .trailing], 18)
                .padding([.top, .bottom], 12)
                .background(Color.surface)
                ScrollView {
                    ForEach(viewModel.state.budgetGroups, id: \.self.id) { group in
                        if !group.budgetList.isEmpty {
                            BudgetGroupItem(
                                group: group
                            )
                            .padding(.top, 14)
                        }
                    }
                }
                .padding([.leading, .trailing], 18)
            }
        }
        .onAppear {
            viewModel.startObserving()
        }
        .onDisappear {
            viewModel.dispose()
        }
    }
}
