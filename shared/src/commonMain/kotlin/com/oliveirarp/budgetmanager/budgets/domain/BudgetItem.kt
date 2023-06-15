package com.oliveirarp.budgetmanager.budgets.domain

import com.oliveirarp.budgetmanager.core.domain.BudgetGroup

data class BudgetItem(
    val id: Long?,
    val budgetGroup: BudgetGroup,
    val name: String,
    val totalMoney: Double
)
