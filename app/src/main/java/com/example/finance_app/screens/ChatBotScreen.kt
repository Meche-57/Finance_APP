package com.example.finance_app.screens

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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.finance_app.components.GeminiAI
import kotlinx.coroutines.launch


@Composable

fun ChatBotScreen(
    income: Double,
    expenses: Double,
    budget: Double,
    goals: Double,
    remainingBudget: Double,
    balance: Double,
    predictedSpending: Double,
    predictedAmount: Double,
    dailySpending: Double

){

    var userInput by remember { mutableStateOf("") }
    var messages by remember { mutableStateOf(emptyList<String>()) }
    val scope = rememberCoroutineScope()



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

                        scope.launch {
                            // Set prompt for the AI on what to respond to
                            val prompt = """
                        
                        You are FinanApp ChatBot, a personal finance assistant. 
                        Here is the user's financial data: 
                        
                        - income : $income
                        -expenses : $expenses
                        -budget : $budget
                        -goals : $goals
                        -remainingBudget : $remainingBudget
                        -balance : $balance
                        -predictedSpending : $predictedSpending
                        -predictedAmount : $predictedAmount
                        -dailySpending : $dailySpending
                        
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

                        }
                    }
                }) {
                    Text("Send")
                    }
                }
     }

}}
