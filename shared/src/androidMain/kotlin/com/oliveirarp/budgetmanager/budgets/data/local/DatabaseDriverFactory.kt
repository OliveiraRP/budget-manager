package com.oliveirarp.budgetmanager.budgets.data.local

import android.content.Context
import com.oliveirarp.budgetmanager.database.BudgetManagerDatabase
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver

actual class DatabaseDriverFactory(
    private val context: Context
) {
    actual fun create(): SqlDriver {
        return AndroidSqliteDriver(BudgetManagerDatabase.Schema, context, "budgetmanager.db")
    }
}