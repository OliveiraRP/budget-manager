package com.oliveirarp.budgetmanager.budgets.data

import com.oliveirarp.budgetmanager.budgets.domain.BudgetItem
import com.oliveirarp.budgetmanager.core.domain.BudgetGroup
import database.Budget

fun Budget.toBudgetItem(): BudgetItem {
    return BudgetItem(
        id = id,
        budgetGroup = BudgetGroup.byId(budget_group),
        name = name,
        totalMoney = total_money
    )
}