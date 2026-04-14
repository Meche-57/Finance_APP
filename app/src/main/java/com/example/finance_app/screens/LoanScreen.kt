package com.example.finance_app.screens


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

import com.example.finance_app.Goal
import com.example.finance_app.components.GoalsCard
import com.example.finance_app.components.LoanCalculator
import com.example.finance_app.ui.theme.Card_Navy

@Preview(showBackground = true, showSystemUi = true)
@Composable





fun LoanScreen() {

    var title by remember { mutableStateOf("") }
    var current by remember { mutableStateOf("") }
    var goal by remember { mutableStateOf("") }

    var goalsList by remember { mutableStateOf(emptyList<Goal>()) }
    var showDialog by remember { mutableStateOf(false) }


    Column(modifier = Modifier.padding(15.dp)) {

        LoanCalculator()

        Spacer(modifier = Modifier.height(25.dp))

        Button(onClick = { showDialog = true }) {
            Text("Add Goal")

        }

        Spacer(modifier = Modifier.height(25.dp))

        goalsList.forEach { item ->

            GoalsCard(

                title = item.title,
                current = item.current,
                goal = item.goal,
                color = Card_Navy
            )

            Spacer(modifier = Modifier.height(10.dp))

        }
    }

    if (showDialog) {

        AlertDialog(
            onDismissRequest = { showDialog = false },// when user closes it,

            title = { Text("Add Goal") },

            text = {

                Column {
                    TextField(
                        value = title,
                        onValueChange = { title = it },
                        label = { Text("Title") }

                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    TextField(
                        value = current,
                        onValueChange = { current = it },
                        label = { Text("Current") }
                    )
                    Spacer(modifier = Modifier.height(10.dp))

                    TextField(
                        value = goal,
                        onValueChange = { goal = it },
                        label = { Text("Goal") }

                    )
                }

            },

            confirmButton = {
                Button(onClick = {
                    goalsList = goalsList + Goal(
                        title = title,
                        current = current.toDoubleOrNull() ?: 0.0,
                        goal = goal.toDoubleOrNull() ?: 0.0
                    )
                    title = ""
                    current = ""
                    goal = ""
                    showDialog = false

                }) {
                    Text("Add")
                }
            },

            dismissButton = {
                Button(onClick = { showDialog = false }) {
                    Text("Cancel")
                }

                goalsList.forEach { item ->
                    Text(text = "Name: ${item.title}, Amount: ${item.current}, Date: ${item.goal}")
                }
            }
        )
    }
}