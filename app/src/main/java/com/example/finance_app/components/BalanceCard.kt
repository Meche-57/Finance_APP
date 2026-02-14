package com.example.finance_app.components

import androidx.compose.foundation.layout.Arrangement
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


@Composable
fun BalanceCard() {
    Card(
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Card_Navy.copy(0.9f)),
        elevation = CardDefaults.cardElevation(defaultElevation = 20.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 5.dp)
            .padding(top = 25.dp)

    ){
// makes the card bigger
        Column(modifier = Modifier.padding(35.dp)){

            Text(
                text = "Total Balance",
                color = Color.LightGray,
                fontWeight = FontWeight.Bold,
                fontSize = 12.sp
            )

            Text(
                text = "$6,000.00",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 32.sp



            )
            Spacer(modifier = Modifier.height(10.dp))
            HorizontalDivider(color = Color.LightGray.copy(0.9f))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center


            ){
                //income
                Column {
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(text = "Income", color = Color.Green, fontSize = 12.sp)

                    Spacer(modifier = Modifier.height(10.dp))
                    Text(text = "$1,500", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 25.sp)
                }

                Spacer(modifier = Modifier.width(115.dp)) // space between easier to freely edit instead of weight or inbetweens


                Column(horizontalAlignment = Alignment.Start) {
                    Spacer(modifier = Modifier.height(10.dp))

                    Text(text = "Expense", color = Color.Red, fontSize = 11.sp)
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(text = "$1,500", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 25.sp)
                }
            }

            //


        }




    }


}

