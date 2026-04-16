package com.example.finance_app.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.finance_app.ui.theme.*


@Composable

fun ForecastProjectionCard(
    predictedBalance: Double,
    endOfMonth: Double,
    dailySpending: Double,
    daysRemaining: Int


) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Card_Navy)

    ) {

        Column(modifier = Modifier.padding(20.dp)) {

            Text(
                text = "Forecast Projection",
                color = Text_White,
                fontSize = 18.sp

            )

            Spacer(modifier = Modifier.height(16.dp))

            SmallBalanceCards(

                title = "Predicted Balance",
                value = "£${predictedBalance.toInt()}",
                desc = "Based on current spending trends",
                modifier = Modifier.fillMaxWidth(),
                status = "£${endOfMonth.toInt()}",
                backgroundColor = Green_Card

            )

            Spacer(modifier = Modifier.height(12.dp))

            SmallBalanceCards(
                title = "End of April",
                value = "£${endOfMonth.toInt()}",
                desc = "Daily Spending: £${dailySpending.toInt()} ",
                modifier = Modifier.fillMaxWidth(),
                status = "",
                backgroundColor = Purple_Card

            )

        }



    }

}