package com.oliveirarp.budgetmanager.budgets.presentation

data class BudgetsState(
    val budgets: List<UiBudget> = emptyList(),
    val budgetGroups: List<UiBudgetGroup> = emptyList()
)
