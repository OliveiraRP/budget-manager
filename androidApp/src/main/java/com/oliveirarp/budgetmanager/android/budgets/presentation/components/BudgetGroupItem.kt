package com.oliveirarp.budgetmanager.android.budgets.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.oliveirarp.budgetmanager.budgets.presentation.UiBudget
import com.oliveirarp.budgetmanager.budgets.presentation.UiBudgetGroup
import com.oliveirarp.budgetmanager.core.presentation.Colors

@Composable
fun BudgetGroupItem(
    group: UiBudgetGroup,
    onSelectBudget: (UiBudget) -> Unit,
    onAddBudget: () -> Unit,
    modifier: Modifier = Modifier

) {
    Column(
        modifier = modifier
            .shadow(
                elevation = 10.dp,
                shape = RoundedCornerShape(20.dp)
            )
            .clip(RoundedCornerShape(20.dp))
            .background(Color(Colors.OxfordBlue))
    ) {

        Row(
            modifier = modifier
                .padding(start = 15.dp, end = 15.dp, top = 15.dp, bottom = 15.dp)
        ) {
            Text(
                text = group.name,
                fontWeight = FontWeight(600),
                fontSize = 25.sp
            )
        }

        group.budgetList.forEach { budget ->
            BudgetItem(
                budget = budget,
                onClick = {
                    onSelectBudget(budget)
                },
                modifier = Modifier
                    .fillMaxWidth()
            )
            Divider(
                thickness = 1.dp,
                color = Color(Colors.RichBlack),
                modifier = modifier
                    .padding(start = 12.dp, end = 12.dp)
            )

        }

        AddBudgetItem(
            onClick = onAddBudget,
            modifier = Modifier
                .fillMaxWidth()
        )

    }

}
