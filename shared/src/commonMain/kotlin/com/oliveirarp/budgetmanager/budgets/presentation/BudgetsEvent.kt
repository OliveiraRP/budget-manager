package com.oliveirarp.budgetmanager.budgets.presentation

sealed class BudgetsEvent {
    object AddBudget : BudgetsEvent()
    data class OpenSelectedBudget(val uiBudget: UiBudget) : BudgetsEvent()
}
