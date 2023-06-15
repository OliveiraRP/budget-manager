package com.oliveirarp.budgetmanager.budgets.presentation

import com.oliveirarp.budgetmanager.core.domain.BudgetGroup

sealed class BudgetsEvent {
    object AddBudget : BudgetsEvent()
    data class AddBudgetChangeName(val name: String) : BudgetsEvent()
    data class AddBudgetChangeGroup(val group: BudgetGroup) : BudgetsEvent()
    data class AddBudgetChangeTotalMoney(val totalMoney: Double) : BudgetsEvent()
    data class OpenSelectedBudget(val uiBudget: UiBudget) : BudgetsEvent()
}
