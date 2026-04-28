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
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.finance_app.components.GoalsCard
import com.example.finance_app.components.LoanCalculator
import com.example.finance_app.ui.theme.Card_Navy
import com.example.finance_app.Goals
import com.example.finance_app.goalsDao
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import com.example.finance_app.ui.theme.Back_Navy
import com.example.finance_app.ui.theme.Box_Navy
import com.example.finance_app.ui.theme.Text_White
import kotlinx.coroutines.launch


@Preview(showBackground = true, showSystemUi = true)
@Composable

fun LoanScreen() {

    var title by remember { mutableStateOf("") }
    var current by remember { mutableStateOf("") }
    var goal by remember { mutableStateOf("") }
    var editingGoal by remember { mutableStateOf<Goals?>(null) } // editGoal
    val scope = rememberCoroutineScope()
    var goalsList by remember { mutableStateOf(emptyList<Goals>()) }
    var showDialog by remember { mutableStateOf(false) }


    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = Back_Navy // background colour

    ) {

        //Add Column to make Header box scrollable
        Column(
            modifier = Modifier.fillMaxSize()
                .verticalScroll(rememberScrollState())
        )
        {

            // Top Header Box
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .height(155.dp)
                    .background(Card_Navy),
                contentAlignment = Alignment.TopStart
            ) {
                // context inside the box
                Text(
                    text = "Loan & Goal",
                    color = Color.White,
                    fontSize = 28.sp,
                    modifier = Modifier.padding(top = 40.dp, start = 20.dp)

                )

                Text(
                    text = "Calculate and compare loan options and Add Goals",
                    color = Color.White,
                    fontSize = 12.sp,
                    modifier = Modifier.padding(top = 85.dp, start = 20.dp))
            }


            LaunchedEffect(Unit) { // show data when app is opened
        goalsList = goalsDao.getAll()
    }


    Column(
        modifier = Modifier
            .padding(horizontal = 15.dp)
            .offset(y = (-20).dp),

    ) {

        LoanCalculator()

        Spacer(modifier = Modifier.height(25.dp))

        Row(modifier = Modifier.fillMaxWidth(),
         horizontalArrangement = Arrangement.SpaceBetween){

            Text(
                text = "Add Your Goals",
                modifier = Modifier.padding(10.dp),
                fontSize = 19.sp,
                color = Color.White)


            Button(onClick = {
            editingGoal = null
            title = ""
            current = ""
            goal = ""
            showDialog = true
        }) {
            Text("Add Goal")

        }
}
        Spacer(modifier = Modifier.height(25.dp))

        goalsList.forEach { item ->

            GoalsCard(

                title = item.title,
                current = item.current,
                goal = item.goal,
                color = Card_Navy,

                // When Edit button is pressed

                editClick = {
                    editingGoal = item
                    title = item.title
                    current = item.current.toString()
                    goal = item.goal.toString()
                    showDialog = true
                }

            )

            Spacer(modifier = Modifier.height(10.dp))

        }
    }

    if (showDialog) {

        AlertDialog(
            onDismissRequest = { showDialog = false },// when user closes it,
            title = { Text("Add Goal") },
            containerColor = Card_Navy,
            textContentColor = Text_White,
            titleContentColor = Text_White,


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
                    scope.launch {

                        if (editingGoal == null) {
                        goalsDao.insertGoal(

                            Goals(
                                title = title,
                                current = current.replace(",", "").toDoubleOrNull() ?: 0.0,
                                goal = goal.replace(",", "").toDoubleOrNull() ?: 0.0
                            )
                        )
                    } else {
                    goalsDao.updateGoal(
                        Goals(
                            id = editingGoal!!.id,
                            title = title,
                            current = current.replace(",", "").toDoubleOrNull() ?: 0.0,
                            goal = goal.replace(",", "").toDoubleOrNull() ?: 0.0

                        )
                    )
                }

                        goalsList = goalsDao.getAll()
                        editingGoal = null
                        title = ""
                        current = ""
                        goal = ""
                        showDialog = false
                    }

                }) {
                    Text(if (editingGoal == null) "Add" else "Update")
                }
            },

            dismissButton = {
                Button(onClick = { showDialog = false }) {
                    Text("Cancel")
                }

            }
        )
    }
}}}