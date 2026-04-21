package com.example.finance_app.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
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
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.TextField
import androidx.compose.runtime.LaunchedEffect
import kotlinx.coroutines.launch
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import com.example.finance_app.components.Charts

@Preview
@Composable
fun TransactionScreen() {


    val scope = rememberCoroutineScope()
    var showDialog by remember { mutableStateOf(false) }
    var name by remember { mutableStateOf("") }
    var amount by remember { mutableStateOf("") }
    var date by remember { mutableStateOf("") }
    var category by remember { mutableStateOf("") }


    // Create an empty List
    var spendingList by remember { mutableStateOf(emptyList<Spending>()) }
    var isDropdownOpen by remember { mutableStateOf(false) }

    // List of categories

    val categories = listOf(
        "Income",
        "Food",
        "Transport",
        "Entertainment",
        "Shopping",
        "Electronics",
        "Bills",
        "Other"
    )
    LaunchedEffect(Unit){
        spendingList = spendingDao.getAll()
    }


    Column(modifier = Modifier.fillMaxSize()
        .verticalScroll(rememberScrollState())) {

        Text(text = " Recent Transaction")

        Spacer(modifier = Modifier.height(10.dp))

        Button(onClick = { showDialog = true }) {
            Text("Add Transaction")
        }

        Spacer(modifier = Modifier.height(10.dp))


        val data = spendingList
            .filter { it.category != "Income" }//
            .groupBy { it.category }
            .map { (_, spendingList) ->
                spendingList.sumOf { it.amount }.toFloat()}

                Column {
                    Text("Transactions")
                        Charts(data,categories)
                }}


                if (showDialog) {

            AlertDialog(
                onDismissRequest = { showDialog = false },
                title = { Text("Add Transaction") },

                text = {
                    Column {
                        TextField(
                            value = name,
                            onValueChange = { name = it },
                            label = { Text("Name") }
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
                            label = { Text("Date") }

                        )
                        Spacer(modifier = Modifier.height(10.dp))

                        Box {
                            Button(
                                onClick = { isDropdownOpen = true },
                                modifier = Modifier.fillMaxWidth()

                            ){
                                Text(text = if (category.isEmpty()) " Select Category" else category)
                            }

                            DropdownMenu(
                                expanded = isDropdownOpen,
                                onDismissRequest = { isDropdownOpen = false },
                                modifier = Modifier.fillMaxWidth()

                            ) {
                                categories.forEach { item ->
                                    DropdownMenuItem(
                                        text = { Text(item) },
                                        onClick = {
                                            category = item
                                            isDropdownOpen = false
                                        }
                                    )
                                }
                            }
                        }
                        }
                    },

                confirmButton = {
                    Button(onClick = {
                        scope.launch {
                            spendingDao.insertSpending(

                                Spending(
                                    name = name,
                                    amount = amount.toDoubleOrNull() ?: 0.0, // Handle null case
                                    date = date,
                                    category = category

                                )
                            )
                            //reload data

                            spendingList = spendingDao.getAll()

                            // clear input

                            name = ""
                            amount = ""
                            date = ""
                            category = ""
                            showDialog = false


                        }
                    })
                    { Text(text = "Add Transaction") }
                },
                dismissButton = {
                    Button(onClick = { showDialog = false }) {
                        Text(text = "Cancel")
                    }
                }
            )
        }


    }