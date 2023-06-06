package com.oliveirarp.budgetmanager.android.budgets.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.oliveirarp.budgetmanager.android.R

@Composable
fun AddBudgetItem(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .clickable { onClick() }
            .padding(start = 15.dp, end = 15.dp, top = 15.dp, bottom = 15.dp)
    ) {
        // TODO: Implement add icon
        Text(text = stringResource(R.string.add_new_budget))
    }
}