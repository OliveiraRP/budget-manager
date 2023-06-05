package com.oliveirarp.budgetmanager.budgets.data

import com.oliveirarp.budgetmanager.budgets.domain.BudgetItem
import database.Budget

fun Budget.toBudgetItem(): BudgetItem {
    return BudgetItem(
        id = id,
        name = name,
        totalMoney = total_money
    )
}