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
import androidx.compose.material.icons.automirrored.filled.TrendingUp
import androidx.compose.material.icons.filled.Assistant
import androidx.compose.material.icons.filled.Calculate
import androidx.compose.material.icons.filled.Checklist
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.finance_app.screens.ChatBotScreen
import com.example.finance_app.screens.ForecastScreen
import com.example.finance_app.screens.LoanScreen
import  androidx.compose.runtime.getValue
import com.example.finance_app.ui.theme.Blue_Card
import com.example.finance_app.ui.theme.Purple_Card
import com.example.finance_app.ui.theme.pieOutline


@Composable 
fun Nav_bar(navController: NavHostController) {

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = navBackStackEntry?.destination?.route

    Scaffold(
        bottomBar = {
            NavigationBar(
                containerColor = Card_Navy,
                contentColor = Blue_Card,

            ) {

                NavigationBarItem(
                    icon = {Icon(Icons.Filled.Home, contentDescription = "Home")},
                    selected = currentScreen == "dashboard",
                    onClick = { navController.navigate("dashboard") },
                    label = { Text("Dashboard") },

                    colors = NavigationBarItemDefaults.colors(
                        indicatorColor = Blue_Card,
                        selectedIconColor = Color.White,
                        selectedTextColor = Color.White,
                        unselectedIconColor = Color.White,
                        unselectedTextColor = Color.White
                    )

                )


                NavigationBarItem(
                    icon = {Icon(Icons.Default.Checklist, contentDescription = "Transaction")},
                    selected = currentScreen == "transaction",
                    onClick = { navController.navigate("transaction") },
                    label = { Text("Activity") },

                    colors = NavigationBarItemDefaults.colors(
                        indicatorColor = Blue_Card,
                    selectedIconColor = Color.White,
                    selectedTextColor = Color.White,
                    unselectedIconColor = Color.White,
                    unselectedTextColor = Color.White
                )
                )


                NavigationBarItem(
                    icon = {Icon(Icons.AutoMirrored.Filled.TrendingUp, contentDescription = "Forecast")},
                    selected = currentScreen == "Forecast",
                    onClick = { navController.navigate("Forecast") },
                    label = { Text("Forecast") },

                            colors = NavigationBarItemDefaults.colors(
                            indicatorColor = Blue_Card,
                    selectedIconColor = Color.White,
                    selectedTextColor = Color.White,
                    unselectedIconColor = Color.White,
                    unselectedTextColor = Color.White
                )
                )

                NavigationBarItem(
                    icon = {Icon(Icons.Default.Calculate, contentDescription = "Loan")},
                    selected = currentScreen == "Loan",
                    onClick = { navController.navigate("Loan") },
                    label = { Text("Loan") },
                    colors = NavigationBarItemDefaults.colors(
                        indicatorColor = Blue_Card,
                        selectedIconColor = Color.White,
                        selectedTextColor = Color.White,
                        unselectedIconColor = Color.White,
                        unselectedTextColor = Color.White
                    )

                )

                NavigationBarItem(
                    icon = {Icon(Icons.Filled.Assistant, contentDescription = "Chat")},
                    selected = currentScreen == "Chat",
                    onClick = { navController.navigate("Chat") },
                    label = { Text("Chat") },
                    colors = NavigationBarItemDefaults.colors(
                        indicatorColor = Blue_Card,
                        selectedIconColor = Color.White,
                        selectedTextColor = Color.White,
                        unselectedIconColor = Color.White,
                        unselectedTextColor = Color.White
                    )
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
            composable("Forecast") { ForecastScreen() }
            composable("Loan") { LoanScreen() }
            composable("Chat") { ChatBotScreen() }

        }
    }
}



