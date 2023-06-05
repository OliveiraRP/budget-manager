package com.oliveirarp.budgetmanager.core.domain

enum class TransactionType(
    val type: String
) {
    INCOME("income"),
    EXPENSE("expense"),
    TRANSFER("transfer")
}