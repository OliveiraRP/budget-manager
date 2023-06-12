package com.oliveirarp.budgetmanager.budgets.presentation

import com.oliveirarp.budgetmanager.core.domain.BudgetGroup

data class UiBudget(
    val id: Long,
    val budgetGroup: BudgetGroup,
    val name: String,
    val totalMoney: Double
)
