package com.example.finance_app.components


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.finance_app.ui.theme.Card_Navy
import com.example.finance_app.ui.theme.Back_Navy
import com.example.finance_app.ui.theme.BudgetOrange
import com.example.finance_app.ui.theme.BudgetYellow
import com.example.finance_app.ui.theme.ExpenseRed
import com.example.finance_app.ui.theme.IncomeGreen
import com.example.finance_app.ui.theme.Text_Grey


@Composable
fun BudgetCard(

    remaining: Int,
    daysRemaining:Int,
    budget: Double,
    expenses: Double,
    editClick: () -> Unit


) {

    val progress = if (budget <=  0) {
        0.0 // progress 0 when budget is 0
    } else {
        expenses / budget // progress cal
    }


    val percent = (progress * 100)

// Progress bar colour
    val progressColor = when {
        budget <= 0 -> Text_Grey
        progress >= 0.0f && progress < 0.8f -> IncomeGreen
        progress >= 0.81f && progress < 0.98f -> BudgetOrange
        progress >= 0.90f && progress < 1f -> ExpenseRed
        else -> ExpenseRed
    }
// Progress Text
    val progressText = when {
        budget <= 0 -> "No Budget Set" // If no budget is set
        progress >= 0.0f && progress < 0.8f -> "On Track"
        progress >= 0.81f && progress < 0.98f -> "Almost Full"
        progress >= 0.99f && progress < 1f -> "Full"
        else -> "Over Budget"
    }

    val progressTextColour = when {
        budget <= 0 -> Text_Grey
        progress >= 0.0f && progress < 0.8f -> IncomeGreen
        progress >= 0.81f && progress < 0.98f -> BudgetOrange
        progress >= 0.90f && progress < 1f -> ExpenseRed
        else -> ExpenseRed
    }



// Dates
    val currentDate = java.util.Calendar.getInstance()
    val monthName =
        java.text.SimpleDateFormat("MMMM", java.util.Locale.getDefault()).format(currentDate.time)

    val year = currentDate.get(java.util.Calendar.YEAR)





    Card(
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Card_Navy.copy(0.9f)),
        elevation = CardDefaults.cardElevation(defaultElevation = 20.dp),
        modifier = Modifier
            .fillMaxWidth()


    ) {
// makes the card bigger
        Column(modifier = Modifier.padding(22.dp)) {
            //add a row to have the status and bar onto the same row

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {


                Text(
                    text = progressText,
                    color = progressTextColour,
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp

                )

                Text(
                    text = "Edit",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp,
                    modifier = Modifier.clickable{editClick()}
                ) }


            Text(
                text = "Budget Status",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )

            Text(
                text = "$monthName $year",
                color = Color.LightGray,
                fontWeight = FontWeight.Bold,
                fontSize = 10.sp
            )



        // The progress bar ()
            Spacer(modifier = Modifier.height(10.dp))


        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(35.dp)
                .background(
                    color = Back_Navy,
                    shape = RoundedCornerShape(10.dp)
                ),
            contentAlignment = Alignment.CenterStart
        ) {

            // color in the bar
            Box(
                modifier = Modifier
                    .fillMaxWidth(progress.toFloat())
                    .fillMaxHeight()
                    .background(
                        color = progressColor,
                        shape = RoundedCornerShape(10.dp)
                    )
            )

            // The number inside the bar
            Text(
                text = "${percent.toInt()}% Used",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 11.sp,
                modifier = Modifier.align(Alignment.Center)
            )
        }

            Spacer(modifier = Modifier.height(10.dp))

// row below
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly


        ) {
            //Remaining
            Column {
                Spacer(modifier = Modifier.height(10.dp))
                Text(text = "Remaining", color = Color.LightGray, fontSize = 12.sp)
                Text(
                    text = "£$remaining",
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
                    text = "$daysRemaining",
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
                    text = if (budget < 0) "-£${-budget}" else "£$budget",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp
                )
            }
        }

        //

    }
    }}










