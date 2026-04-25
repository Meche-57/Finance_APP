package com.example.finance_app.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Column
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
import com.example.finance_app.components.BarChart
import com.example.finance_app.components.Charts
import android.app.DatePickerDialog
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons

import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.ui.platform.LocalContext
import com.example.finance_app.ui.theme.Card_Navy
import com.example.finance_app.ui.theme.Text_White
import java.util.Calendar

import androidx.compose.material3.TextFieldDefaults
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color


import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.finance_app.budgetDao
import com.example.finance_app.components.BalanceCard
import com.example.finance_app.ui.theme.Back_Navy
import com.example.finance_app.ui.theme.Box_Navy

import com.example.finance_app.ui.theme.textField


@Preview
@Composable
fun TransactionScreen() {

    var income by remember { mutableStateOf(0.0) }
    var expenses by remember { mutableStateOf(0.0) }
    var balance by remember { mutableStateOf(0.0) }


    val scope = rememberCoroutineScope()
    var showDialog by remember { mutableStateOf(false) }
    var name by remember { mutableStateOf("") }
    var amount by remember { mutableStateOf("") }
    var date by remember { mutableStateOf("") }
    var category by remember { mutableStateOf("") }



    // Create an empty List
    var spendingList by remember { mutableStateOf(emptyList<Spending>()) }
    var isDropdownOpen by remember { mutableStateOf(false) }

    val context = LocalContext.current
    val calendar = Calendar.getInstance()

    val datePickerDialog = DatePickerDialog(
        context,
        { _, year, month, dayOfMonth -> // _ = ignored unused parameters in kotlin lambds
            date = "%02d/%02d/%04d".format(dayOfMonth, month + 1, year)
        },
        calendar.get(Calendar.YEAR),
        calendar.get(Calendar.MONTH),
        calendar.get(Calendar.DAY_OF_MONTH)
    )
    // List of categories

    val dropdownCategories = listOf(
        "Income",
        "Food",
        "Transport",
        "Entertainment",
        "Shopping",
        "Electronics",
        "Bills",
        "Other"
    )

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Back_Navy
    ){
        Column(
            modifier = Modifier.fillMaxSize()
                .verticalScroll(rememberScrollState())
        ){

    Box(modifier = Modifier.fillMaxSize()) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(155.dp)
                .background(Box_Navy),
            contentAlignment = Alignment.TopStart
        ) {

        }

        LaunchedEffect(Unit){
        spendingList = spendingDao.getAll()


    }

        // income is category Income
        income = spendingList
            .filter { it.category == "Income" }
            .sumOf { it.amount }

        expenses = spendingList
            .filter { it.category != "Income" }
            .sumOf { it.amount }


        balance = income - expenses

        //Positions of the cards
        Column(
            modifier = Modifier
                .padding(horizontal = 15.dp)
                .padding(top = 70.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ){

        BalanceCard(
            income = income,
            expenses = expenses,
            balance = balance


        )
        //Button to Add Transactions
        Button(onClick = { showDialog = true },
            shape = RoundedCornerShape(16.dp),
            modifier =  Modifier.fillMaxWidth()
                .padding(6.dp))


        {
            Text(text = " Add Transaction",
                textAlign = TextAlign.Left,
                modifier = Modifier.padding(18.dp)
               )

            Icon(
                imageVector = Icons.Default.KeyboardArrowRight,
                contentDescription = "Add Transaction",

            )
        }

// Pie chart Data

        val chartCategories = mutableListOf<String>()
        val chartValues = mutableListOf<Float>()

        val data = spendingList
            .filter { it.category != "Income" }//
            .groupBy { it.category }

        for ((category, list) in data) {

            chartCategories.add(category ?: "Other") // adds the category but if unknown add to other
            chartValues.add(list.sumOf { it.amount }.toFloat()) // adds the amount of the category
        }
        Charts(chartValues, chartCategories)



            // Monthly BarChart

        val monthLabels = listOf(
            "Jan", "Feb", "Mar", "Apr", "May", "Jun",
            "Jul"
        )

        // Initialize a list of 7 zeros to store monthly totals
        val monthlyTotals = MutableList(7) { 0f }

        spendingList
            .filter { it.category != "Income" }
            .forEach { item ->
                val parts = item.date?.split("/")  // splits the date format

                if (parts != null && parts.size > 1){
                    // -1 as months as months start at 1 but list at 0
                    val month = parts[1].toInt() - 1

                    if (month in 0..6){ //checks if the month is in the list
                        monthlyTotals[month] += item.amount.toFloat() // adds the amount to the list
                    }
                }
            }

        BarChart(monthlyTotals, monthLabels)


        if (showDialog) {

            // Title of the Alert Box
            AlertDialog(
                onDismissRequest = { showDialog = false },
                title = { Text("Add Transaction") },
                containerColor = Card_Navy,
                textContentColor = Text_White,
                titleContentColor = Text_White,



                text = {
                    Column {

                        // Name of Transaction
                        TextField(
                            value = name,
                            colors = TextFieldDefaults.colors(
                                unfocusedContainerColor = (textField)),
                            onValueChange = { name = it },
                            label = { Text("Name")


                            }

                        )


                        Spacer(modifier = Modifier.height(10.dp))


                        // Amount of Transaction
                        TextField(
                            value = amount,
                            colors = TextFieldDefaults.colors(
                                unfocusedContainerColor = (textField)),
                            onValueChange = { amount = it },
                            label = { Text("Amount") },

                            )
                        Spacer(modifier = Modifier.height(10.dp))

                        // Date picker
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { datePickerDialog.show() }
                        ) {
                            TextField(

                                value = date,

                                onValueChange = {},
                                readOnly = true,
                                label = { Text("Date") },
                                enabled = false, // prevents typing
                                modifier = Modifier.fillMaxWidth(),
                                colors = TextFieldDefaults.colors(
                                    unfocusedContainerColor = (textField))

                            )
                        }
                        Spacer(modifier = Modifier.height(10.dp))

                        // Dropdown Menu for Categories
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { isDropdownOpen = true }

                        ) {
                            TextField(
                                value = category,
                                onValueChange = {},
                                readOnly = true,
                                label = { Text("Category") },
                                enabled = false, // prevents typing
                                modifier = Modifier.fillMaxWidth(),
                                colors = TextFieldDefaults.colors(
                                    unfocusedContainerColor = (textField))

                            )
                        }

                        DropdownMenu(
                            expanded = isDropdownOpen,
                            onDismissRequest = { isDropdownOpen = false },
                            containerColor = textField

                        ) {
                            //Goes thought the dropdown categories and adds them to the dropdown
                            dropdownCategories.forEach { item ->
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
                },

                        confirmButton = {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween

                            ) {
                                Button(onClick = { showDialog = false }) {
                                    Text(text = "Cancel")
                                }
                                Button(onClick = {
                                    scope.launch {
                                        spendingDao.insertSpending( // Adds it to the spending Database

                                            Spending(
                                                name = name,
                                                amount = amount.toDoubleOrNull()
                                                    ?: 0.0, // Handle null case
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
                                }) {
                                    Text("Done")


                                }}})}}}}}}