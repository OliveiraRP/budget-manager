package com.oliveirarp.budgetmanager.android.budgets.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.oliveirarp.budgetmanager.budgets.presentation.UiBudget

@Composable
fun BudgetItem(
    budget: UiBudget,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .clickable { onClick() }
            .padding(start = 15.dp, end = 15.dp, top = 15.dp, bottom = 15.dp)
    ) {
        Text(text = budget.name)
        Spacer(modifier = Modifier.weight(1f))
        Text(text = budget.totalMoney.toString())
    }
}