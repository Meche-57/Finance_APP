package com.example.finance_app.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.finance_app.ui.theme.Card_Navy



@Composable
fun BarChart(values: List<Float>, labels: List<String>) {

    val max = values.maxOrNull() ?: return

    Card(
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Card_Navy
            .copy(0.9f)),
        elevation = CardDefaults.cardElevation(defaultElevation = 20.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)

    ){

    Column(
        modifier = Modifier.padding(16.dp)){
        Text(
            text = "Monthly Spending",
            modifier = Modifier.fillMaxWidth()
                .padding(12.dp),
            textAlign = TextAlign.Center,
            fontSize =  20.sp)


        Spacer(modifier = Modifier.height(10.dp))



        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        ) {

            val totalWidth = size.width
            val totalHeight = size.height
            val numberOfBars = values.size


            val slotWidth = totalWidth / numberOfBars
            val barWidth = slotWidth * 0.6f

            values.forEachIndexed { i, value ->

                // how tall the bar should be
                val barHeight = (value / max) * totalHeight

                // horizontal position of the bar
                val xPosition = i * barWidth * 1.8f

                // vertical position
                val yPosition = totalHeight - barHeight

                drawRoundRect(
                    color = Color(0xFF2196F3),
                    topLeft = Offset(xPosition, yPosition),
                    size = Size(barWidth, barHeight),
                    cornerRadius = CornerRadius(16f)
                )
            }
        }


        // LABELS
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            labels.forEach {
                Text(
                    text= it,
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center


                )
            }

        }

        Spacer(modifier = Modifier.height(10.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
        ) {
            values.forEach {
                Text(
                    text = "£${it.toInt()}",
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center
                )
            }
        }


    }}}