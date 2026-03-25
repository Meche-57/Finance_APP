package com.example.finance_app.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.finance_app.components.ForecastBalanceCard
import com.example.finance_app.components.ForecastProjectionCard
import com.example.finance_app.components.GoalsCard
import com.example.finance_app.ui.theme.*

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ForecastScreen() {


    Column(modifier = Modifier.padding(16.dp)) {

        Text("Forecast")

        Spacer(modifier = Modifier.height(16.dp))

        ForecastProjectionCard()


        Spacer(modifier = Modifier.height(10.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start


        ) {
            ForecastBalanceCard(
                title = "Current Balance",
                value = "$12,465",
                desc = "Day 13 of 30",
                modifier = Modifier.width(170.dp),
                backgroundColor = Blue_Card
            )

            Spacer(modifier = Modifier.width(10.dp))
            ForecastBalanceCard(

                title = "Average Spending ",
                value = "$12,465",
                desc = "per day",
                modifier = Modifier.width(170.dp),
                backgroundColor = Purple_Card

            )


        }

        Spacer(modifier = Modifier.fillMaxWidth())

        GoalsCard()
    }
}

