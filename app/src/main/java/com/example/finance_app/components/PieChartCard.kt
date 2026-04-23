package com.example.finance_app.components

import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.finance_app.ui.theme.Card_Navy


fun categoryColors(category:String): Color {
    return when (category){


       "Income" -> Color(0xFFF44336)
        "Food" ->Color(0xFFE91E63)
        "Transport" -> Color(0xFF9C27B0)
        "Entertainment" -> Color(0xFF673AB7)
        "Shopping" -> Color(0xFF3F51B5)
        "Electronics" -> Color(0xFF2196F3)
        "Bills" -> Color(0xFF03A9F4)
        "Other" -> Color(0xFF00BCD4)
        else -> Color.Gray }}

@Composable

fun Charts(values: List<Float>, categories: List<String>) {

    val total = values.sum()

    if (total == 0f ) return
    Card(
        shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = Card_Navy
                    .copy(0.9f)),
                elevation = CardDefaults.cardElevation(defaultElevation = 20.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)

            ){

                Column (
                    modifier = Modifier.padding(16.dp)){

                    // Title of the pie chart
                    Text(
                        text = "Spending By Category",
                        modifier = Modifier.fillMaxWidth()
                            .padding(12.dp),
                        textAlign = TextAlign.Center,
                        fontSize =  20.sp


                )

                    Spacer(modifier = Modifier.height(10.dp))


                    // To move the pie chart easier use a box
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center


                    ){

                    // drawing area for the pie chart
                    Canvas(modifier = Modifier.size(200.dp)){

                        var startAngle = 0f
                        values.forEachIndexed { i,value -> // loop through the values and categories
                            val sliceSize = (value/total) * 360f // calculate the size of the slice




                            drawArc(
                                color = categoryColors(categories[i]), // colors for each slice
                                startAngle = startAngle,
                                sweepAngle = sliceSize,
                                useCenter = true, // draws the circle in the middle

                            )



                            drawArc(
                                color = Color.White, // colors for each slice
                                startAngle = startAngle,
                                sweepAngle = sliceSize,
                                useCenter = true, // draws the circle in the middle
                                style = Stroke(width = 2f)

                            )
                        startAngle += sliceSize




                        }

                    }

                }
                        Column {
                        categories.forEachIndexed { i, category ->
                            Row( modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 6.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            )

                            {
                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ){

                                // The bullet point with the categoryColor
                                Box(
                                    modifier = Modifier
                                        .size(10.dp)
                                        .background(categoryColors(category),
                                            shape = CircleShape

                                ))

                                // Category Name
                                Text(text = category,
                                    modifier = Modifier.padding(start = 8.dp))

                            }

                                // Value of Category
                                Text("£%.2f".format(values[i]))




                    }

}}}}}
