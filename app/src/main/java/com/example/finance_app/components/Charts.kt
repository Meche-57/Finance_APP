package com.example.finance_app.components

import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text


@Composable
fun Charts(values: List<Float>, categories: List<String>) {

    val colors = listOf(
        Color(0xFFF44336),
        Color(0xFFE91E63),
        Color(0xFF9C27B0),
        Color(0xFF673AB7),
        Color(0xFF3F51B5),
        Color(0xFF2196F3),
        Color(0xFF03A9F4),
        Color(0xFF00BCD4)



    )

    val total = values.sum()
    if (total == 0f ) return

    Column {
// drawing area for the pie char
    Canvas(modifier = Modifier.size(200.dp)){
        // start angle
        var startAngle = 0f


        values.forEachIndexed { i,value ->
            val sliceSize = (value/total) * 360f

// draws the slice of the circle
    drawArc(
        color = colors[i%colors.size], // colors for each slice
        startAngle = startAngle,
        sweepAngle = sliceSize,
        useCenter = true, // draws the circle in the middle
        size = Size(size.width, size.height) // size of the circle
    )

    startAngle += sliceSize
}
}


        // Label

        categories.forEachIndexed { i, category ->
            Row{
                Box(
                    modifier = Modifier
                        .size(10.dp)
                        .background(colors[i % colors.size])
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = category,
                )
            }
        }


}}