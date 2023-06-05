package com.oliveirarp.budgetmanager.budgets.presentation

import com.oliveirarp.budgetmanager.budgets.domain.AddBudget
import com.oliveirarp.budgetmanager.budgets.domain.BudgetDataSource
import com.oliveirarp.budgetmanager.budgets.domain.GetBudgets
import com.oliveirarp.budgetmanager.core.util.Resource
import com.oliveirarp.budgetmanager.core.util.toCommonStateFlow
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class BudgetsViewModel(
    private val getBudgets: GetBudgets,
    private val addBudget: AddBudget,
    private val budgetDataSource: BudgetDataSource,
    private val coroutineScope: CoroutineScope?
) {
    private val viewModelScope = coroutineScope ?: CoroutineScope(Dispatchers.Main)

    private val _state = MutableStateFlow(BudgetsState())
    val state = combine(
        _state,
        budgetDataSource.getBudgets()
    ) { state, budgets ->
        if (state.budgets != budgets) {
            state.copy(
                budgets = budgets.mapNotNull { item ->
                    UiBudget(
                        id = item.id ?: return@mapNotNull null,
                        name = item.name,
                        totalMoney = item.totalMoney
                    )
                }
            )
        } else state
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), BudgetsState())
        .toCommonStateFlow()

    fun onEvent(event: BudgetsEvent) {
        when (event) {
            BudgetsEvent.AddBudget -> addBudget()
            is BudgetsEvent.OpenSelectedBudget -> TODO("Create budget screen")
            else -> {}
        }
    }

    private fun addBudget() {
        viewModelScope.launch {
            // TODO: Implement actual add budget logic
            val result = addBudget.execute(
                name = "New budget",
                totalMoney = 420.00
            )
            when (result) {
                is Resource.Success -> {
                    // TODO: Check if there must be a state update here
                    // Probably to update BudgetState.budgetList
                }

                is Resource.Error -> {
                    // TODO: Error handling
                }

                else -> {}
            }
        }
    }
}