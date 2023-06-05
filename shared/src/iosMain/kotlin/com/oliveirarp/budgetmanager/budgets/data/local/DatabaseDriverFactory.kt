package com.oliveirarp.budgetmanager.budgets.data.local

import com.oliveirarp.budgetmanager.database.BudgetManagerDatabase
import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.drivers.native.NativeSqliteDriver

actual class DatabaseDriverFactory {
    actual fun create(): SqlDriver {
        return NativeSqliteDriver(BudgetManagerDatabase.Schema, "budgetmanager.db")
    }
}