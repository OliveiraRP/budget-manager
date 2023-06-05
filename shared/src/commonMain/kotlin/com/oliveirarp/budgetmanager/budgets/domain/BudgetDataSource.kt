package com.oliveirarp.budgetmanager.budgets.domain

import com.oliveirarp.budgetmanager.core.util.CommonFlow

interface BudgetDataSource {
    fun getBudgets(): CommonFlow<List<BudgetItem>>
    suspend fun insertBudgetItem(item: BudgetItem)
}