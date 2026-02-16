package com.example.finance_app.components
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.finance_app.screens.HomeScreen
import com.example.finance_app.screens.TransactionScreen
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.ui.graphics.Color
import com.example.finance_app.ui.theme.Card_Navy
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.Icon


@Composable 
fun Nav_bar(navController: NavHostController) {

    Scaffold(
        bottomBar = {
            NavigationBar(
                containerColor = Card_Navy,
                contentColor = Color.White
            ) {

                NavigationBarItem(
                    icon = {Icon(Icons.Filled.Home, contentDescription = "Home")},
                    selected = false,
                    onClick = { navController.navigate("dashboard") },
                    label = { Text("Dashboard") }
                )


                NavigationBarItem(
                    icon = {Icon(Icons.Filled.List, contentDescription = "Transaction")},
                    selected = false,
                    onClick = { navController.navigate("transaction") },
                    label = { Text("Transactions") }
                )
            }
        }

        // inner padding is required in scaffolding had issues because of this missing line
    ) { innerPadding ->

        NavHost(
            navController = navController,
            startDestination = "dashboard",
            modifier = Modifier.padding(innerPadding)

        ) {
            composable("dashboard") { HomeScreen() }
            composable("transaction") { TransactionScreen() }
        }
    }
}



