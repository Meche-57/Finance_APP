package com.example.finance_app.components

import android.R
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.finance_app.ui.theme.Card_Navy
import androidx.compose.material3.LinearProgressIndicator
import com.example.finance_app.ui.theme.Back_Navy

import com.example.finance_app.ui.theme.BudgetOrange
import com.example.finance_app.ui.theme.IncomeGreen
import com.example.finance_app.ui.theme.Text_Grey

@Composable
fun BudgetCard() {
    Card(
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Card_Navy.copy(0.9f)),
        elevation = CardDefaults.cardElevation(defaultElevation = 20.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 5.dp)
            .padding(top = 5.dp) // top part of the card

    ) {
// makes the card bigger
        Column(modifier = Modifier.padding(20.dp)) {
            //add a row to have the status and bar onto the same row

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Column(modifier = Modifier.weight(1f)) {

                    Text(
                        text = "On Track",
                        color = Color.Green,
                        fontWeight = FontWeight.Bold,
                        fontSize = 10.sp

                    )


                    Text(
                        text = "Budget Status",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )

                    Text(
                        text = "February 2026",
                        color = Color.LightGray,
                        fontWeight = FontWeight.Bold,
                        fontSize = 10.sp
                    )


                }
                // The progress bar ()

                Box(
                    modifier = Modifier
                        .width(130.dp)
                        .height(35.dp)
                        .background(
                            color = Back_Navy,
                            shape = RoundedCornerShape(10.dp)),
                    contentAlignment = Alignment.CenterStart
                ) {

                    // color in the bar
                    Box(
                        modifier = Modifier
                            .fillMaxHeight()
                            .fillMaxWidth(0.18f) // 18%
                            .background(
                                color = BudgetOrange,
                                shape = RoundedCornerShape(10.dp)
                            )
                    )

                    // The number inside the bar
                    Text(
                        text = "18%",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }

// row below
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start


            ) {
                //Remaining
                Column {
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(text = "Remaining", color = Color.LightGray, fontSize = 12.sp)
                    Text(
                        text = "$1,108",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp
                    )
                }

                Spacer(modifier = Modifier.width(25.dp))


                Column(horizontalAlignment = Alignment.Start) {
                    Spacer(modifier = Modifier.height(10.dp))

                    // Days Left

                    Text(text = "Days Left", color = Color.LightGray, fontSize = 12.sp)
                    Text(
                        text = "17",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp
                    )
                }

                Spacer(modifier = Modifier.width(20.dp))


                Column(horizontalAlignment = Alignment.Start) {
                    Spacer(modifier = Modifier.height(10.dp))

                    // Monthly Budget

                    Text(text = "Monthly Budget", color = Color.LightGray, fontSize = 12.sp)
                    Text(
                        text = "$4,892 / $6,000",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp
                    )
                }
            }

            //


        }
    }}









