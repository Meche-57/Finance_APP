package com.example.finance_app.components


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.finance_app.ui.theme.*
import androidx.compose.material3.LinearProgressIndicator

@Preview (showBackground = true)
@Composable

fun GoalsCard(){

    Card(
        modifier = Modifier.padding(16.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors( containerColor = Card_Navy) )

    {

        Column(modifier = Modifier.padding(16.dp)) {

            Text(
                text = "Saving Goals",
                color = Text_White,
                fontSize = 18.sp

            )

            Spacer(modifier= Modifier.padding(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start


            ) {

                Spacer(modifier = Modifier.padding(2.dp))
                Text(text = "Emergency Fund", color = Text_White)

                Spacer(modifier = Modifier.width(100.dp))

                Spacer(modifier = Modifier.padding(8.dp))
                Text(text = "$3,000 / $10,000 ", color = Text_White)


            }
            Column(modifier = Modifier.padding(10.dp)) {

                LinearProgressIndicator(
                    progress = { 0.35f },
                    modifier = Modifier.fillMaxWidth(),
                )

                Spacer(modifier = Modifier.padding(2.dp))

                Text(text = "35% complete" , color = Text_White)


            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start


            ) {

                Spacer(modifier = Modifier.padding(2.dp))
                Text(text = "New Car", color = Text_White)

                Spacer(modifier = Modifier.width(150.dp))

                Spacer(modifier = Modifier.padding(8.dp))
                Text(text = "$3,000 / $10,000 ", color = Text_White)


            }
            Column(modifier = Modifier.padding(10.dp)) {

                LinearProgressIndicator(
                    progress = { 0.35f },
                    modifier = Modifier.fillMaxWidth(),
                )

                Spacer(modifier = Modifier.padding(2.dp))

                Text(text = "35% complete" , color = Text_White)


            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start


            ) {

                Spacer(modifier = Modifier.padding(2.dp))
                Text(text = "Vacation", color = Text_White)

                Spacer(modifier = Modifier.width(150.dp))

                Spacer(modifier = Modifier.padding(8.dp))
                Text(text = "$1,000 / $5,000 ", color = Text_White)


            }
            Column(modifier = Modifier.padding(10.dp)) {

                LinearProgressIndicator(
                    progress = { 0.35f },
                    modifier = Modifier.fillMaxWidth(),
                )

                Spacer(modifier = Modifier.padding(2.dp))

                Text(text = "35% complete" , color = Text_White)


            }






        }

    }}






