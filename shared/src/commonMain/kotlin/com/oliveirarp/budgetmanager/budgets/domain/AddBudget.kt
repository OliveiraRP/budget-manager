package com.oliveirarp.budgetmanager.budgets.domain

import com.oliveirarp.budgetmanager.core.domain.BudgetGroup
import com.oliveirarp.budgetmanager.core.util.Resource

class AddBudget(
    private val budgetDataSource: BudgetDataSource
) {

    suspend fun execute(
        name: String,
        budgetGroup: BudgetGroup,
        totalMoney: Double
    ): Resource<String> {
        return try {
            budgetDataSource.insertBudgetItem(
                BudgetItem(
                    id = null,
                    budgetGroup = budgetGroup,
                    name = name,
                    totalMoney = totalMoney
                )
            )
            Resource.Success("Successfully added new budget")
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(e)
        }
    }
}