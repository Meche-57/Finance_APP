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
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue


import com.example.finance_app.components.BalanceCard
import com.example.finance_app.components.BudgetCard
import com.example.finance_app.components.Recent_Activity
import com.example.finance_app.spendingDao
import com.example.finance_app.ui.theme.Back_Navy
import com.example.finance_app.ui.theme.Box_Navy
import com.example.finance_app.Spending



@Composable

fun HomeScreen() {

    var income by remember { mutableStateOf(0.0) }
    var expenses by remember { mutableStateOf(0.0) }
    var balance by remember { mutableStateOf(0.0) }
    var spendingList by remember { mutableStateOf(emptyList<Spending>()) }
    var remaining by remember { mutableStateOf(0) }
    var budget by remember { mutableStateOf(0.0) }



    // val cannot change
    val currentDate = java.util.Calendar.getInstance()
    val currentDay = currentDate.get(java.util.Calendar.DAY_OF_MONTH)
    val maxDays = currentDate.getActualMaximum(java.util.Calendar.DAY_OF_MONTH)
    val daysRemaining = maxDays - currentDay
    val scrollState = rememberScrollState()


    LaunchedEffect(Unit) {

        spendingList = spendingDao.getAll()

        // income is category Income
        income = spendingList
            .filter { it.category == "Income" }
            .sumOf { it.amount }

        expenses = spendingList
            .filter { it.category != "Income" }
            .sumOf { it.amount }


        balance = income - expenses

        budget = 550.0
        remaining = (budget - expenses).toInt()

    }



        Surface(
            modifier = Modifier
                .fillMaxSize(),
            color = Back_Navy // background colour


        ) {

            // This is the box at the top of the screen

            Box(modifier = Modifier.fillMaxSize()) {

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
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
                        .align(Alignment.TopCenter)
                        .verticalScroll(scrollState)
                        .padding(top = 95.dp) // Adjust this value to control the overlap
                        .padding(horizontal = 20.dp),
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


                    )
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