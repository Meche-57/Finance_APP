package com.example.finance_app.screens

import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.finance_app.Spending
import com.example.finance_app.spendingDao
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material3.Button
import androidx.compose.material3.TextField
import kotlinx.coroutines.launch
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

@Preview
@Composable
fun TransactionScreen() {


    val scope = rememberCoroutineScope()

    var name by remember { mutableStateOf("") }
    var amount by remember { mutableStateOf("") }
    var date by remember { mutableStateOf("") }
    var category by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }

    // Create an empty List
    var spendingList by remember { mutableStateOf(emptyList<Spending>()) }


    Column(modifier = Modifier.fillMaxSize()) {

        Text(text = " Recent Transaction")

        Spacer(modifier = Modifier.height(10.dp))

        TextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Name") },

            )

        Spacer(modifier = Modifier.height(10.dp))

        TextField(
            value = amount,
            onValueChange = { amount = it },
            label = { Text("Amount") },

            )
        Spacer(modifier = Modifier.height(10.dp))

        TextField(
            value = date,
            onValueChange = { date = it },
            label = { Text("Date") },

            )
        Spacer(modifier = Modifier.height(10.dp))

        TextField(
            value = category,
            onValueChange = { category = it },
            label = { Text("Category") },
        )

        Spacer(modifier = Modifier.height(10.dp))

        TextField(

            value = description,
            onValueChange = { description = it },
            label = { Text("Description") },
        )



        Spacer(modifier = Modifier.height(12.dp))

        Button(onClick = {
            scope.launch {

                spendingDao.insertSpending(
                    Spending(
                        name = name,
                        amount = amount.toDoubleOrNull() ?: 0.0, // Handle null case
                        date = date,
                        category = category,
                        description = description

                    )
                )
                //reload data

                spendingList = spendingDao.getAll()

                // clear input

                name = ""
                amount = ""
                date = ""
                category = ""
                description = ""

            }
        })
        { Text(text = "Add Transaction") }


        Spacer(modifier = Modifier.height(10.dp))

        Button(onClick = {
            scope.launch {
                spendingList = spendingDao.getAll()
            }
        }) {
            Text("Show Transactions")


        }


        Spacer(modifier = Modifier.height(10.dp))


        spendingList.forEach { item ->
            Text(text = "Name: ${item.name}, Amount: ${item.amount}, Date: ${item.date}, Category: ${item.category}, Description: ${item.description}")

        }
    }
}

