package com.oliveirarp.budgetmanager.budgets.domain

import com.oliveirarp.budgetmanager.core.util.CommonFlow
import com.oliveirarp.budgetmanager.core.util.Resource

class GetBudgets(
    private val budgetDataSource: BudgetDataSource
) {

    suspend fun execute(): Resource<CommonFlow<List<BudgetItem>>> {
        return try {
            val allBudgets = budgetDataSource.getBudgets()
            Resource.Success(allBudgets)
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(e)
        }
    }

}