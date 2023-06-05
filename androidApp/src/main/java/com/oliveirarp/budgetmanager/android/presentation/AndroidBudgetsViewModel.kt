package com.oliveirarp.budgetmanager.android.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oliveirarp.budgetmanager.budgets.domain.AddBudget
import com.oliveirarp.budgetmanager.budgets.domain.BudgetDataSource
import com.oliveirarp.budgetmanager.budgets.domain.GetBudgets
import com.oliveirarp.budgetmanager.budgets.presentation.BudgetsEvent
import com.oliveirarp.budgetmanager.budgets.presentation.BudgetsViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AndroidBudgetsViewModel @Inject constructor(
    private val getBudgets: GetBudgets,
    private val addBudget: AddBudget,
    private val budgetDataSource: BudgetDataSource
) : ViewModel() {

    private val viewModel by lazy {
        BudgetsViewModel(
            getBudgets = getBudgets,
            addBudget = addBudget,
            budgetDataSource = budgetDataSource,
            coroutineScope = viewModelScope
        )
    }

    val state = viewModel.state

    fun onEvent(event: BudgetsEvent) {
        viewModel.onEvent(event)
    }
}