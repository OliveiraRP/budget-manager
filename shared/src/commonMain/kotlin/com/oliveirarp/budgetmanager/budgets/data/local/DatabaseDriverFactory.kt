package com.oliveirarp.budgetmanager.budgets.data.local

import com.squareup.sqldelight.db.SqlDriver

expect class DatabaseDriverFactory {
    fun create(): SqlDriver
}