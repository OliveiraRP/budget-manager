package com.oliveirarp.budgetmanager.budgets.presentation

import com.oliveirarp.budgetmanager.budgets.domain.AddBudget
import com.oliveirarp.budgetmanager.budgets.domain.BudgetDataSource
import com.oliveirarp.budgetmanager.budgets.domain.GetBudgets
import com.oliveirarp.budgetmanager.core.domain.BudgetGroup
import com.oliveirarp.budgetmanager.core.util.Resource
import com.oliveirarp.budgetmanager.core.util.toCommonStateFlow
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
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
            val allBudgets: List<UiBudget> =
                budgets.mapNotNull { item ->
                    UiBudget(
                        id = item.id ?: return@mapNotNull null,
                        budgetGroup = item.budgetGroup,
                        name = item.name,
                        totalMoney = item.totalMoney
                    )
                }

            val allBudgetGroups: List<UiBudgetGroup> =
                BudgetGroup.values().map { group ->
                    UiBudgetGroup(
                        id = group.id,
                        name = group.groupName,
                        budgetList = allBudgets.filter { budget ->
                            budget.budgetGroup.id == group.id
                        }
                    )
                }

            state.copy(
                budgets = allBudgets,
                budgetGroups = allBudgetGroups
            )
        } else state
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), BudgetsState())
        .toCommonStateFlow()

    fun onEvent(event: BudgetsEvent) {
        when (event) {
            BudgetsEvent.AddBudget -> addBudget(state.value)
            is BudgetsEvent.AddBudgetChangeName -> {
                _state.update { it.copy(
                    addBudgetName = event.name
                ) }
            }
            is BudgetsEvent.AddBudgetChangeGroup -> {
                _state.update { it.copy(
                    addBudgetGroup = event.group
                ) }
            }
            is BudgetsEvent.AddBudgetChangeTotalMoney -> {
                _state.update { it.copy(
                    addBudgetTotalMoney = event.totalMoney
                ) }
            }
            else -> {}
        }
    }

    private fun addBudget(state: BudgetsState) {
        viewModelScope.launch {
            val result = addBudget.execute(
                name = state.addBudgetName,
                budgetGroup = state.addBudgetGroup,
                totalMoney = state.addBudgetTotalMoney
            )
            when (result) {
                is Resource.Success -> {
                    // TODO: Check if there must be a state update here
                }

                is Resource.Error -> {
                    // TODO: Error handling
                }
            }
        }
    }
}