package com.oliveirarp.budgetmanager.android.di

import android.app.Application
import com.oliveirarp.budgetmanager.budgets.data.SqlDelightBudgetDataSource
import com.oliveirarp.budgetmanager.budgets.data.local.DatabaseDriverFactory
import com.oliveirarp.budgetmanager.budgets.domain.AddBudget
import com.oliveirarp.budgetmanager.budgets.domain.BudgetDataSource
import com.oliveirarp.budgetmanager.budgets.domain.GetBudgets
import com.oliveirarp.budgetmanager.database.BudgetManagerDatabase
import com.squareup.sqldelight.db.SqlDriver
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabaseDriver(app: Application): SqlDriver {
        return DatabaseDriverFactory(app).create()
    }

    @Singleton
    @Provides
    fun provideBudgetDataSource(driver: SqlDriver): BudgetDataSource {
        return SqlDelightBudgetDataSource(BudgetManagerDatabase(driver))
    }

    @Singleton
    @Provides
    fun provideGetBudgetsUseCase(
        dataSource: BudgetDataSource
    ): GetBudgets {
        return GetBudgets(dataSource)
    }

    @Singleton
    @Provides
    fun provideAddBudgetUseCase(
        dataSource: BudgetDataSource
    ): AddBudget {
        return AddBudget(dataSource)
    }
}