package com.example.finance_app.screens

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.finance_app.AppDatabase
import com.example.finance_app.budgetDao
import com.example.finance_app.components.GeminiAI
import com.example.finance_app.goalsDao
import com.example.finance_app.spendingDao
import kotlinx.coroutines.launch


@Composable

fun ChatBotScreen(){

    var budget by remember { mutableStateOf(0.0) }
    var remainingBudget by remember { mutableStateOf(0.0) }
    var goals by remember { mutableStateOf(0.0) }

    var income by remember { mutableStateOf(0.0) }
    var expenses by remember { mutableStateOf(0.0) }
    var balance by remember { mutableStateOf(0.0) }

    var userInput by remember { mutableStateOf("") }
    var messages by remember { mutableStateOf(listOf<String>()) }
    var isLoading by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()


    LaunchedEffect(Unit) {
        val spendingList = spendingDao.getAll()
        val goalsList = goalsDao.getAll()
        val budgetList = budgetDao.getAll()


        income = spendingList.filter { it.category == "Income" }
            .sumOf { it.amount }
        expenses = spendingList.filter { it.category!= "Income" }
            .sumOf { it.amount }
        balance = income - expenses


        budget = budgetList.firstOrNull()?.budgetGoal ?: 0.0

        goals = goalsList.sumOf{ it.current}
        remainingBudget = budget - expenses

        }



    Column(
        modifier = Modifier
            .fillMaxSize()

    ) {
        Column(modifier = Modifier.padding(16.dp)) {

            Text("FinanApp ChatBot")
            Spacer(modifier = Modifier.height(16.dp))
Column(
     modifier = Modifier
         .verticalScroll(rememberScrollState())) {
    messages.forEach {
        Text(text = it)
        Spacer(modifier = Modifier.height(8.dp))
    }
    if (isLoading) {
        Text(text = "FinanBot is analysing...")
    }


            Row{
         TextField(
             value = userInput,
             onValueChange = { userInput = it},
             label = { Text("Type your message here") }


         )

                Button(onClick = {

                    if (userInput.isNotBlank()) {
                        val question = userInput
                        messages = messages + "You: $question"
                        userInput = ""
                        isLoading = true


                        scope.launch {
                            // Set prompt for the AI on what to respond to
                            val prompt = """
                        
                        You are FinanApp ChatBot, a personal finance assistant. 
                        Here is the user's financial data: 
                        
                        - income : $income
                        -expenses : $expenses
                        -balance : $balance
                      
                        
                       Rules:
                       - Only answer finance related questions
                       - Use data from above when responding
                       - If not related say: 
                        "I'm sorry, I can only assist with finance-related questions."
                        - Keep answers short and helpful
                        - Give practical advice when possible
                        
                        User Question: $question 
                        """.trimIndent()

                            val reply = GeminiAI.askGemini(prompt)
                            messages = messages + "Bot:$reply"
                            isLoading = false

                        }
                    }
                }) {
                    Text("Send")
                    }
                }
     }

}}}


