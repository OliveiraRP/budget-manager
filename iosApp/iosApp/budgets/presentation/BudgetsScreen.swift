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

    init(budgetDataSource: BudgetDataSource, getBudgetsUseCase: GetBudgets, addBudgetUseCase: AddBudget) {
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
        VStack {
            BudgetList(
                budgetList: viewModel.state.budgets,
                onAddBudget: {
                    viewModel.onEvent(event: BudgetsEvent.AddBudget())
                }
            )
        }
        .shadow(radius: 12)
        .padding(18)
        .onAppear {
            viewModel.startObserving()
        }
        .onDisappear {
            viewModel.dispose()
        }
    }
}
