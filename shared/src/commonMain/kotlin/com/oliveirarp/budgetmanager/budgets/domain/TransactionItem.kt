package com.oliveirarp.budgetmanager.budgets.domain

import com.oliveirarp.budgetmanager.core.domain.TransactionType

data class TransactionItem(
    val id: Long?,
    val budget: BudgetItem,
    val type: TransactionType,
    val value: Double
    // TODO: Implement timestamp variable
    //val timestamp:
)