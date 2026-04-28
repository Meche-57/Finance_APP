package com.example.finance_app.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDownward
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.finance_app.ui.theme.BudgetRed
import com.example.finance_app.ui.theme.Card_Navy
import com.example.finance_app.ui.theme.ExpenseRed
import com.example.finance_app.ui.theme.Green_Card


@Composable
fun BalanceCard(
    income: Double,
    expenses: Double,
    balance : Double,


    ) {
    Card(
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Card_Navy.copy(0.9f)),
        elevation = CardDefaults.cardElevation(defaultElevation = 20.dp),
        modifier = Modifier
            .fillMaxWidth()


    ){
// makes the card bigger
        Column(modifier = Modifier.padding(38.dp)) {

            // balance Text

            Text(
                text = "Total Balance",
                color = Color.LightGray,
                fontWeight = FontWeight.Bold,
                fontSize = 21.sp
            )

            Spacer(modifier = Modifier.height(10.dp))

            //whole number with commas %,.2f format
            Text(
                text = String.format("£%,.2f", balance),
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 32.sp


            )
            Spacer(modifier = Modifier.height(10.dp))
            HorizontalDivider(color = Color.LightGray.copy(0.9f))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween


            ) {


                //income
                Column {
                    Row(verticalAlignment = Alignment.CenterVertically) {

                        Spacer(modifier = Modifier.height(50.dp))

                        // Income icon and text
                        Box(
                            modifier = Modifier.size(22.dp)
                                .background(Green_Card),
                            contentAlignment = Alignment.Center
                        ) {

                            Icon(
                                imageVector = Icons.Default.ArrowUpward,
                                contentDescription = "Income",
                                modifier = Modifier.size(15.dp)
                            )
                        }

                        // space  between arrow and Income

                        Spacer(modifier = Modifier.width(8.dp))

                        Text(
                            text = "Income",
                            color = Color.Green,
                            fontSize = 12.sp
                        )
                    }

                    Text(
                        text = String.format("£%,.2f",income),
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 25.sp
                    )
                }



                Spacer(modifier = Modifier.width(8.dp)) // space between easier to freely edit instead of weight or inbetweens


                Column {
                    Row(verticalAlignment = Alignment.CenterVertically) {

                        Spacer(modifier = Modifier.height(50.dp))
                        // expense box icon and text
                        Box(
                            modifier = Modifier.size(22.dp)
                                .background(BudgetRed),
                            contentAlignment = Alignment.Center
                        ) {

                            Icon(
                                imageVector = Icons.Default.ArrowDownward,
                                contentDescription = "Expense",
                                tint = Color.White,
                                modifier = Modifier.size(15.dp)
                            )
                        }

                        Spacer(modifier = Modifier.width(8.dp))

                        Text(
                            text = "Expense",
                            color = ExpenseRed,
                            fontSize = 12.sp
                        )
                    }


                    Text(
                        text = String.format("£%,.2f",expenses),
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 25.sp
                    )
                }




            }
        }



    }}


