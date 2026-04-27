package com.example.finance_app.screens
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.finance_app.Spending
import com.example.finance_app.components.SmallBalanceCards
import com.example.finance_app.components.ForecastProjectionCard
import com.example.finance_app.ui.theme.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import com.example.finance_app.components.AIRecommendCard
import com.example.finance_app.components.GeminiAI
import com.example.finance_app.spendingDao


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ForecastScreen() {

    var scrollState = rememberScrollState()
    var spendingList by remember { mutableStateOf(emptyList<Spending>()) }
    var aiSuggestion by remember { mutableStateOf("Loading Advice") }

    var income by remember { mutableStateOf(0.0) }
    var expenses by remember { mutableStateOf(0.0) }
    var balance  by remember { mutableStateOf(0.0) }
    var dailySpending by remember { mutableStateOf(0.0) }
    var predictedBalance by remember { mutableStateOf(0.0) }
    // runs when screen opens

    // date logic

    // current date
    val currentDate = java.util.Calendar.getInstance()
    // current day
    val currentDay = currentDate.get(java.util.Calendar.DAY_OF_MONTH)
    // total days
    val maxDays = currentDate.getActualMaximum(java.util.Calendar.DAY_OF_MONTH)
    // days left in month
    val daysRemaining = maxDays - currentDay

    LaunchedEffect(Unit) {

        // all transactions from Room database

        spendingList = spendingDao.getAll()

        // calculating total income

        income = spendingList.filter { it.category == "Income" }
            .sumOf { it.amount }

        // total expenses

        expenses = spendingList.filter { it.category != "Income" }
            .sumOf { it.amount }

        // current balance

        balance = income - expenses




        // daily spending

        dailySpending = expenses / 30

        // predicted balance

        predictedBalance = balance - (dailySpending * daysRemaining)


            // Set prompt for the AI on what to respond to
            val prompt = """
                        
                        You are FinanApp ChatBot, a personal finance assistant. 
                        Here is the user's financial data: 
                        
                        - income : $income
                        -expenses : $expenses
                        -balance : $balance
                        -daily spending : $dailySpending
                        -predicted balance : $predictedBalance
                        -days remaining : $daysRemaining
                        -current day : $currentDay
                        -max days : $maxDays
                       
                      
                     
                       Rules:
                       - Only answer finance related questions
                       - Use data from above when responding
                       - If not related say: 
                        "I'm sorry, I can only assist with finance-related questions."
                        - Keep answers short and helpful
                        - Give practical advice when possible
                        - Only give advice not more help 
                        
         
                        """.trimIndent()

        aiSuggestion = GeminiAI.askGemini(prompt)


    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(scrollState)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {

            Text("Forecast")
            Spacer(modifier = Modifier.height(16.dp))


            ForecastProjectionCard(
                predictedBalance = predictedBalance,
                endOfMonth = balance,
                dailySpending = dailySpending,
                daysRemaining = daysRemaining
            )


            Spacer(modifier = Modifier.height(10.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start


            ) {
                SmallBalanceCards(
                    title = "Current Balance",
                    value = "£${balance.toInt()}",
                    desc = "Day $currentDay of $maxDays",
                    modifier = Modifier.width(170.dp),
                    status = "",
                    backgroundColor = Blue_Card

                )

                Spacer(modifier = Modifier.width(10.dp))
                SmallBalanceCards(

                    title = "Average Spending ",
                    value = "£${dailySpending.toInt()}",
                    desc = "per day",
                    modifier = Modifier.width(170.dp),
                    status = "",
                    backgroundColor = Purple_Card
                )
            }

            Spacer(modifier = Modifier.height(10.dp))



            AIRecommendCard(aiSuggestion)



            Spacer(modifier = Modifier.height(10.dp))



        }
    }
}
