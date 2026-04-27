package com.example.finance_app.screens


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.finance_app.budgetDao
import com.example.finance_app.components.GeminiAI
import com.example.finance_app.goalsDao
import com.example.finance_app.spendingDao
import com.example.finance_app.ui.theme.Card_Navy
import kotlinx.coroutines.launch
import com.example.finance_app.ui.theme.Purple_Card


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

        // all transactions from Room database
        val spendingList = spendingDao.getAll()
        val goalsList = goalsDao.getAll()
        val budgetList = budgetDao.getAll()

// calculating total income, expenses and balance
        income = spendingList.filter { it.category == "Income" }
            .sumOf { it.amount }
        expenses = spendingList.filter { it.category!= "Income" }
            .sumOf { it.amount }
        balance = income - expenses


        budget = budgetList.firstOrNull()?.budgetGoal ?: 0.0

        goals = goalsList.sumOf{ it.current}
        remainingBudget = budget - expenses

        messages = listOf("Bot: Hello, I'm FinanBot :D", "Bot: How can I help you with your finances?",
            "Bot: Disclaimer:Responses may not be accurate, please use me as guidance only")

        }


        Column(modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()){

            // Chatbot title
            Text("FinanApp ChatBot")
            Spacer(modifier = Modifier.height(16.dp))


// Messages
Column(
     modifier = Modifier
         .weight(1f)
         .verticalScroll(rememberScrollState())) {

    messages.forEach { message ->
        val user = message.startsWith("You:")
        val Disclaimer = message.startsWith("Bot: Disclaimer")

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = if (user) Arrangement.End else Arrangement.Start // Aligns messages to the end if User
        ) {
            Box(
                modifier = Modifier

                    .padding(8.dp)
                    .background(
                        when {
                            user -> Purple_Card
                            Disclaimer -> Color.Gray
                            else -> Card_Navy// Background for bubble
                        },
                        shape = RoundedCornerShape(12.dp)
                    )
                    .padding(10.dp)

            ) {
                Text(
                    text = message.replace("You:", "").replace("Bot:", "").replace("Disclaimer:", ""),
                    color = Color.White
                )
            }
        }
    }




    if (isLoading) {
        Text(text = "FinanBot is analysing...")
    }
}


            Row{
         TextField(
             value = userInput,
             onValueChange = { userInput = it},
             label = { Text("Type your message here") },
             modifier = Modifier.weight(1f) // Keeps Input area at the bottom


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

}


