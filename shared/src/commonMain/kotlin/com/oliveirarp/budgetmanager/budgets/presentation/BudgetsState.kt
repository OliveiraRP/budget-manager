package com.oliveirarp.budgetmanager.budgets.presentation

import com.oliveirarp.budgetmanager.core.domain.BudgetGroup

data class BudgetsState(
    val budgets: List<UiBudget> = emptyList(),
    val budgetGroups: List<UiBudgetGroup> = emptyList(),

    val addBudgetName: String = "",
    val addBudgetGroup: BudgetGroup = BudgetGroup.OTHER,
    val addBudgetTotalMoney: Double = 0.0
)
