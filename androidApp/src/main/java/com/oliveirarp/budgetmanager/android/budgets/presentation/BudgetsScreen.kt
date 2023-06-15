package com.oliveirarp.budgetmanager.android.budgets.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.oliveirarp.budgetmanager.android.budgets.presentation.components.BudgetGroupItem
import com.oliveirarp.budgetmanager.budgets.presentation.BudgetsEvent
import com.oliveirarp.budgetmanager.budgets.presentation.BudgetsState

@Composable
fun BudgetsScreen(
    state: BudgetsState,
    onEvent: (BudgetsEvent) -> Unit
) {
    Scaffold { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(28.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            items(state.budgetGroups) { budgetGroup ->
                if (budgetGroup.budgetList.isNotEmpty()) {
                    BudgetGroupItem(
                        group = budgetGroup,
                        onSelectBudget = { onEvent(BudgetsEvent.OpenSelectedBudget(it)) }
                    )
                }
            }
        }
    }
}