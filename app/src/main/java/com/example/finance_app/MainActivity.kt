package com.example.finance_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.finance_app.components.Nav_bar
import androidx.navigation.compose.rememberNavController
import com.example.finance_app.screens.HomeScreen
import com.example.finance_app.ui.theme.Finance_APPTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Finance_APPTheme {

                val navController = rememberNavController()


                Surface(
                    modifier = Modifier.fillMaxSize(),
                     color = MaterialTheme.colorScheme.background
                ) {

                    Nav_bar(navController)

            }


                }
            }
        }
    }




@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Finance_APPTheme {
        HomeScreen()
    }
}