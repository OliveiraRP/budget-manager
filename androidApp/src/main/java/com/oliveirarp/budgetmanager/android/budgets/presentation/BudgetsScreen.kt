package com.oliveirarp.budgetmanager.android.budgets.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.oliveirarp.budgetmanager.android.budgets.presentation.components.BudgetList
import com.oliveirarp.budgetmanager.budgets.presentation.BudgetsEvent
import com.oliveirarp.budgetmanager.budgets.presentation.BudgetsState

@Composable
fun BudgetsScreen(
    state: BudgetsState,
    onEvent: (BudgetsEvent) -> Unit
) {
    Scaffold { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(28.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            BudgetList(
                // TODO: Implement budget groups
                group = "Group",
                budgetList = state.budgets,
                onSelectBudget = { onEvent(BudgetsEvent.OpenSelectedBudget(it)) },
                onAddBudget = { onEvent(BudgetsEvent.AddBudget) }
            )
        }
    }
}