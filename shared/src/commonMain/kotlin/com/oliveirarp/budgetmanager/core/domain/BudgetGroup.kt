package com.oliveirarp.budgetmanager.core.domain

import com.oliveirarp.budgetmanager.budgets.presentation.UiBudgetGroup

enum class BudgetGroup(
    val id: Long,
    val groupName: String
) {
    AVAILABLE(1, "Available"),
    BANK(2, "Bank"),
    SAVINGS(3, "Savings"),
    INVESTMENT(4, "Investment"),
    TEMPORARY(5, "Temporary"),
    OTHER(6, "Other");

    companion object {
        fun byId(id: Long): BudgetGroup {
            return values().find { it.id == id }
                ?: throw IllegalArgumentException("Invalid budget group")
        }

        val allBudgetGroups: List<BudgetGroup>
            get() = BudgetGroup.values().toList()
    }
}