//
//  IOSBudgetsViewModel.swift
//  iosApp
//
//  Created by Rafael Oliveira on 07/06/2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import shared

extension BudgetsScreen {
    @MainActor class IOSBudgetsViewModel: ObservableObject {
        private var budgetDataSource: BudgetDataSource
        private var getBudgetsUseCase: GetBudgets
        private var addBudgetUseCase: AddBudget

        private let viewModel: BudgetsViewModel

        @Published var state: BudgetsState = .init(
            budgets: [UiBudget(id: 0, budgetGroup: BudgetGroup.available, name: "", totalMoney: 0)],
            budgetGroups: [UiBudgetGroup(id: 0, name: "", budgetList: [])]
        )
        private var handle: DisposableHandle?

        init(budgetDatasource: BudgetDataSource,
             getBudgetsUseCase: GetBudgets,
             addBudgetUseCase: AddBudget)
        {
            budgetDataSource = budgetDatasource
            self.getBudgetsUseCase = getBudgetsUseCase
            self.addBudgetUseCase = addBudgetUseCase
            viewModel = BudgetsViewModel(
                getBudgets: getBudgetsUseCase,
                addBudget: addBudgetUseCase,
                budgetDataSource: budgetDataSource,
                coroutineScope: nil
            )
        }

        func onEvent(event: BudgetsEvent) {
            viewModel.onEvent(event: event)
        }

        func startObserving() {
            handle = viewModel.state.subscribe(onCollect: { state in
                if let state = state {
                    self.state = state
                }
            })
        }

        func dispose() {
            handle?.dispose()
        }
    }
}
