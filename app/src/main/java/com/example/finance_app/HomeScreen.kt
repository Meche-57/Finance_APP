package com.example.finance_app

import android.R.attr.top
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.Modifier
import androidx.compose.material3.Surface
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable

fun HomeScreen() {
    Surface(modifier = Modifier.fillMaxSize()){

            // This is the box at the top of the screen

            Box(modifier = Modifier.fillMaxSize()){

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .background(Color.DarkGray),
                contentAlignment = Alignment.TopStart
            ) {
                // context inside the box

                Text(
                    text = "Hello User,",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 32.sp,
                    modifier = Modifier.padding(top = 90.dp, start = 20.dp)

                    )
            }

                Box(
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(top = 150.dp)
                        .size(width = 320.dp, height = 200.dp)
                        .background(Color.Black, shape = RoundedCornerShape(20.dp)),
                    contentAlignment = Alignment.TopStart


                ) {

                    Text(
                        text = "Total Balance",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 32.sp,
                        modifier = Modifier.padding(10.dp),

                        )


                }


            }

        }

    }





@Composable
@Preview(showBackground = true)
fun PreviewHomeScreen() {
    HomeScreen()
}