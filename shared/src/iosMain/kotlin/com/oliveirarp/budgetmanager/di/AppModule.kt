package com.oliveirarp.budgetmanager.di

import com.oliveirarp.budgetmanager.budgets.data.SqlDelightBudgetDataSource
import com.oliveirarp.budgetmanager.budgets.data.local.DatabaseDriverFactory
import com.oliveirarp.budgetmanager.budgets.domain.AddBudget
import com.oliveirarp.budgetmanager.budgets.domain.BudgetDataSource
import com.oliveirarp.budgetmanager.budgets.domain.GetBudgets
import com.oliveirarp.budgetmanager.database.BudgetManagerDatabase

class AppModule {

    val budgetDataSource: BudgetDataSource by lazy {
        SqlDelightBudgetDataSource(
            BudgetManagerDatabase(
                DatabaseDriverFactory().create()
            )
        )
    }

    val getBudgetsUseCase: GetBudgets by lazy {
        GetBudgets(budgetDataSource)
    }

    val addBudgetUseCase: AddBudget by lazy {
        AddBudget(budgetDataSource)
    }
}