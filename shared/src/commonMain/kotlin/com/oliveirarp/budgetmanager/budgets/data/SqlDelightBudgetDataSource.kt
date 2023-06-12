package com.oliveirarp.budgetmanager.budgets.data

import com.oliveirarp.budgetmanager.budgets.domain.BudgetDataSource
import com.oliveirarp.budgetmanager.budgets.domain.BudgetItem
import com.oliveirarp.budgetmanager.core.util.CommonFlow
import com.oliveirarp.budgetmanager.core.util.toCommonFlow
import com.oliveirarp.budgetmanager.database.BudgetManagerDatabase
import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import kotlinx.coroutines.flow.map

class SqlDelightBudgetDataSource(
    db: BudgetManagerDatabase
) : BudgetDataSource {

    private val queries = db.budgetsQueries

    override fun getBudgets(): CommonFlow<List<BudgetItem>> {
        return queries
            .getBudgets()
            .asFlow()
            .mapToList()
            .map { budget ->
                budget.map { it.toBudgetItem() }
            }
            .toCommonFlow()
    }

    override suspend fun insertBudgetItem(item: BudgetItem) {
        return queries.insertBudget(
            id = item.id,
            budget_group = item.budgetGroup.id,
            name = item.name,
            total_money = item.totalMoney
        )
    }

}