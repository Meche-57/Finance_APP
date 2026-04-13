package com.example.finance_app.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Arrangement.Center
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.finance_app.ui.theme.Box_Navy
import com.example.finance_app.ui.theme.Card_Navy
import com.example.finance_app.ui.theme.Text_White


@Composable

fun LoanCalculator() {

    var amount by remember { mutableStateOf("") }
    var interest by remember { mutableStateOf("") }
    var term by remember { mutableStateOf("") }


    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Box_Navy)

    ) {

        // Make a row to have two columns
        Row(modifier = Modifier.padding(16.dp)) {

            // Same card inputs as Transaction Screen

            Column(modifier = Modifier.weight(1f)) {
                Text("Loan Calculator", color = Text_White)

                Spacer(modifier = Modifier.height(15.dp))

                FinanceInput(
                    value = amount,
                    onValueChange = { amount = it },
                    label = "Loan Amount"

                )
                FinanceInput(
                    value = interest,
                    onValueChange = {},
                    label = "Interest Rate"
                )
                FinanceInput(
                    value = term,
                    onValueChange = {},
                    label = "Loan Term"

                )
            }



            Column(horizontalAlignment = Alignment.CenterHorizontally) {

                //push it down to the center
                Spacer(modifier = Modifier.padding(55.dp))

                // Results Card

                Card(
                    colors = CardDefaults.cardColors(containerColor = Card_Navy.copy(0.9f)),
                    elevation = CardDefaults.cardElevation(defaultElevation = 20.dp),
                    modifier = Modifier
                        .width(185.dp)
                        .height(150.dp)


                ) { // Have to have another column for the Text
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    )
                    {

                        // Results Values and Text

                        Text(
                            text = "Monthly Payment",
                            color = Text_White,
                            textAlign = TextAlign.Center
                        )

                        Text(
                            text = "$2,500"
                        )

                        Spacer(modifier = Modifier.padding(8.dp))

                        Text(
                            text = "Total Repayments",
                            color = Text_White,
                            textAlign = TextAlign.Center
                        )

                        Text(
                            text = "$10,000",
                            color = Text_White
                        )

                    }

                }


            }


        }
    }
}