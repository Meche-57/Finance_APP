package com.example.finance_app.screens
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
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.finance_app.components.BalanceCard
import com.example.finance_app.components.BudgetCard
import com.example.finance_app.components.Recent_Activity
import com.example.finance_app.ui.theme.Back_Navy
import com.example.finance_app.ui.theme.Box_Navy
import androidx.navigation.compose.rememberNavController


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

            //Positions of the cards
            Column (
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(top = 130.dp) // Adjust this value to control the overlap
                    .padding(horizontal = 20.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                BalanceCard()
                BudgetCard()
                Recent_Activity()






            }


            }
        }
    }

@Composable
@Preview(showBackground = true)
fun PreviewHomeScreen() {
    HomeScreen()
}