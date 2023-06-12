package com.oliveirarp.budgetmanager.budgets.presentation

data class UiBudgetGroup(
    val id: Long,
    val name: String,
    val budgetList: List<UiBudget>
)