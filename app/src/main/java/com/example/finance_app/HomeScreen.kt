package com.example.finance_app

import android.R
import android.R.attr.top
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.*
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.finance_app.ui.theme.Back_Navy
import com.example.finance_app.ui.theme.Box_Navy
import com.example.finance_app.ui.theme.Card_Navy




@Composable

fun HomeScreen() {
    Surface(modifier = Modifier
        .fillMaxSize(),
        color = Back_Navy // background colour


    ){


        // This is the box at the top of the screen

        Box(modifier = Modifier.fillMaxSize()) {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .background(Box_Navy),
                contentAlignment = Alignment.TopStart
            ) {
                // context inside the box

                Text(
                    text = "Hello User,",
                    color = Color.White,
                    fontSize = 32.sp,
                    modifier = Modifier.padding(top = 90.dp, start = 20.dp)

                )
            }

            // OVERLAPPING CARD: Position the BalanceCard here
            // It is aligned to the top and then pushed down to create the overlap
            Box(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(top = 130.dp) // Adjust this value to control the overlap
            ) {
                BalanceCard()

            }
        }
    }
}

@Composable
fun BalanceCard() {
    Card(
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Card_Navy.copy(0.9f)),
        elevation = CardDefaults.cardElevation(defaultElevation = 20.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .padding(top = 30.dp)

    ){
// makes the card bigger
                Column(modifier = Modifier.padding(40.dp)){

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
                            Spacer(modifier = Modifier.height(20.dp))
                            Text(text = "Income", color = Color.Green, fontSize = 12.sp)

                            Spacer(modifier = Modifier.height(10.dp))
                            Text(text = "$1,500", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 25.sp)
                        }

                        Spacer(modifier = Modifier.width(120.dp)) // spacce between easier to freely edit instead of weight or inbetweens


                        Column(horizontalAlignment = Alignment.Start) {
                            Spacer(modifier = Modifier.height(20.dp))

                            Text(text = "Expense", color = Color.Red, fontSize = 12.sp)
                            Spacer(modifier = Modifier.height(10.dp))
                            Text(text = "$1,500", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 25.sp)
                        }
                    }

                    //


                // use box for the budget status instead
               // Box(
                  // modifier = Modifier
                       //.width(100.dp)
                       //.height(30.dp)
                       //.background(Card_Navy, RoundedCornerShape(8.dp)),
                       // contentAlignment = Alignment.Center ) {
                    //Text("18%", color = Color.White, fontWeight = FontWeight.Bold)
                //}




                        //.align(Alignment.TopStart)
                       // .padding(top = 150.dp)
                        //.size(width = 320.dp, height = 200.dp)
                       // .background(Color.Black, shape = RoundedCornerShape(20.dp)),
                  //  contentAlignment = Alignment.TopStart


               // ) {

                   // Text(
                      //  text = "Total Balance",
                        //color = Color.White,
                        //fontWeight = FontWeight.Bold,
                        //fontSize = 32.sp,
                       // modifier = Modifier.padding(10.dp),

                       // )


                }


            }

        }







@Composable
@Preview(showBackground = true)
fun PreviewHomeScreen() {
    HomeScreen()
}