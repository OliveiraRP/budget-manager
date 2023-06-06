package com.oliveirarp.budgetmanager.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Shapes
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.oliveirarp.budgetmanager.android.budgets.presentation.AndroidBudgetsViewModel
import com.oliveirarp.budgetmanager.android.budgets.presentation.BudgetsScreen
import com.oliveirarp.budgetmanager.android.core.presentation.Routes
import com.oliveirarp.budgetmanager.budgets.presentation.BudgetsEvent
import dagger.hilt.android.AndroidEntryPoint

@Composable
fun BudgetManagerTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = com.oliveirarp.budgetmanager.android.core.theme.darkColors
    val typography = Typography(
        body1 = TextStyle(
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp
        )
    )
    val shapes = Shapes(
        small = RoundedCornerShape(4.dp),
        medium = RoundedCornerShape(4.dp),
        large = RoundedCornerShape(0.dp)
    )

    MaterialTheme(
        colors = colors,
        typography = typography,
        shapes = shapes,
        content = content
    )
}

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BudgetManagerTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    BudgetsRoot()
                }
            }
        }
    }
}

@Composable
fun BudgetsRoot() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Routes.BUDGET_LIST
    ) {
        composable(route = Routes.BUDGET_LIST) {
            val viewModel = hiltViewModel<AndroidBudgetsViewModel>()
            val state by viewModel.state.collectAsState()
            BudgetsScreen(
                state = state,
                onEvent = { event ->
                    when (event) {
                        is BudgetsEvent.OpenSelectedBudget -> {
                            navController.navigate(
                                Routes.BUDGET_DETAIL + "/${event.uiBudget.id}"
                            )
                        }

                        else -> viewModel.onEvent(event)
                    }
                }
            )
        }
        composable(
            route = Routes.BUDGET_DETAIL + "/{budgetId}",
            arguments = listOf(
                navArgument("budgetId") {
                    type = NavType.StringType
                    defaultValue = "0"
                }
            )
        ) { backStackEntry ->
            val budgetId = backStackEntry.arguments?.getString("budgetId") ?: "0"
            // TODO: Implement budget screen
            Text(text = "Budget $budgetId")
        }
    }
}