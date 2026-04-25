package com.example.finance_app.screens
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.Modifier
import androidx.compose.material3.Surface
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import com.example.finance_app.Budget
import com.example.finance_app.Goals


import com.example.finance_app.components.BalanceCard
import com.example.finance_app.components.BudgetCard
import com.example.finance_app.components.Recent_Activity
import com.example.finance_app.spendingDao
import com.example.finance_app.ui.theme.Back_Navy
import com.example.finance_app.ui.theme.Box_Navy
import com.example.finance_app.Spending
import com.example.finance_app.budgetDao
import kotlinx.coroutines.launch


@Composable

fun HomeScreen() {

    var income by remember { mutableStateOf(0.0) }
    var expenses by remember { mutableStateOf(0.0) }
    var balance by remember { mutableStateOf(0.0) }
    var spendingList by remember { mutableStateOf(emptyList<Spending>()) }
    var remaining by remember { mutableStateOf(0) }
    var budget by remember { mutableStateOf(0.0) }

    var showBudgetDialog by remember { mutableStateOf(false) }
    var budgetInput by remember { mutableStateOf("") }
    var budgetList by remember { mutableStateOf(emptyList<Budget>()) }


    val scope = rememberCoroutineScope()


    // val cannot change
    val currentDate = java.util.Calendar.getInstance()
    val currentDay = currentDate.get(java.util.Calendar.DAY_OF_MONTH)
    val maxDays = currentDate.getActualMaximum(java.util.Calendar.DAY_OF_MONTH)
    val daysRemaining = maxDays - currentDay


    LaunchedEffect(Unit) {

        budgetList = budgetDao.getAll()
        spendingList = spendingDao.getAll()

        // income is category Income
        income = spendingList
            .filter { it.category == "Income" }
            .sumOf { it.amount }

        expenses = spendingList
            .filter { it.category != "Income" }
            .sumOf { it.amount }

        budget = budgetList.firstOrNull()?.budgetGoal ?: 0.0


        balance = income - expenses

// remaining should dbe 0 when budget is not set
        remaining = if (budget <= 0) {
            0
        } else {
            (budget - expenses).toInt()

        }
    }

        Surface(
            modifier = Modifier
                .fillMaxSize(),
            color = Back_Navy // background colour

        ) {

            //Add Column to make Header box scrollable
            Column(
                modifier = Modifier.fillMaxSize()
                .verticalScroll(rememberScrollState()))
            {

                // Top Header Box
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .height(155.dp)
                        .background(Box_Navy),
                    contentAlignment = Alignment.TopStart
                ) {
                    // context inside the box

                    Text(
                        text = "Hello User ,",
                        color = Color.White,
                        fontSize = 32.sp,
                        modifier = Modifier.padding(top = 60.dp, start = 20.dp)

                    )
                }
                //Positions of the cards
                Column(
                    modifier = Modifier
                        .padding(horizontal = 20.dp)
                        .offset(y = (-35).dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    BalanceCard(
                        income = income,
                        expenses = expenses,
                        balance = balance


                    )


                    BudgetCard(
                        remaining = remaining,
                        daysRemaining = daysRemaining,
                        budget = budget,
                        expenses = expenses,
                       editClick = { budgetInput = budget.toString(); showBudgetDialog = true }


                    )

                    if (showBudgetDialog) {
                        AlertDialog(
                            onDismissRequest = { showBudgetDialog = false },
                            title = { Text("Edit Budget") },
                            text = {
                                TextField(
                                    value = budgetInput,
                                    onValueChange = { budgetInput = it },
                                    label = { Text("New Budget") }


                                )
                            },

                            confirmButton = {
                                Button(onClick = {
                                    scope.launch {
                                        val newBudget = budgetInput.toDoubleOrNull() ?: 0.0
                                        budgetDao.insertBudget(Budget(budgetGoal = newBudget))
                                        budget = newBudget
                                        remaining = if (budget <= 0) {
                                            0
                                        } else {
                                            (budget - expenses).toInt()
                                        }
                                        showBudgetDialog = false


                                    }}) {
                                    Text("Save")
                                }

                            },
                            dismissButton = {
                                Button(onClick = { showBudgetDialog = false }) {
                                    Text("Cancel")
                                }
                            }



                        )
                    }

                    Recent_Activity()


                }


            }
        }
    }

@Composable
@Preview(showBackground = true)
fun PreviewHomeScreen() {
    HomeScreen()

}