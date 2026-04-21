package com.example.finance_app.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.finance_app.Spending
import com.example.finance_app.spendingDao
import com.example.finance_app.ui.theme.Card_Navy
import kotlinx.coroutines.launch
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.sp
import com.example.finance_app.ui.theme.Blue_Card
import com.example.finance_app.ui.theme.ExpenseRed
import com.example.finance_app.ui.theme.IncomeGreen
import com.example.finance_app.ui.theme.Purple_Card
import com.example.finance_app.ui.theme.Text_Grey
import com.example.finance_app.ui.theme.Text_White


fun CategoryIcon(category: String?) : ImageVector{
    return when (category) {

    "Food" -> Icons.Default.ThumbUp
    "Transport" -> Icons.Default.Check
    "Entertainment" -> Icons.Default.Add
    "Shopping" -> Icons.Default.ShoppingCart
    "Electronics" -> Icons.Default.Phone
    "Bills" -> Icons.Default.Menu
    "Income" -> Icons.Default.Lock
    "Other" -> Icons.Default.Build

    else -> Icons.Default.Edit


}}

@Composable
fun Recent_Activity() {
    var spendingList by remember { mutableStateOf(emptyList<Spending>()) }


    LaunchedEffect(Unit) {
        spendingList = spendingDao.getAll()
    }

    Text(text = "Recent Activity")

    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(10.dp),
        colors = CardDefaults.cardColors(containerColor = Card_Navy)){


        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically

        ){

            Column(modifier = Modifier.padding(2.dp)) {

// reverse to see recent transactions first
    spendingList.takeLast(8).reversed().forEach { item ->

        Column(modifier = Modifier.padding(12.dp)){
            val spendingColour = if(item.category == "Income") {
                IncomeGreen
            } else {
                ExpenseRed
            }

            Column(modifier = Modifier.padding(12.dp)){


        Row ( modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween) {

            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier.size(30.dp)
                    .clip(RoundedCornerShape(5.dp))
                    .background(Purple_Card.copy(alpha = 0.5f)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = CategoryIcon(item.category),
                        contentDescription = item.category,
                        tint = spendingColour,
                        modifier = Modifier.size(12.dp)
                    )
                }

                //name of purchase output
                Text(
                    text = " ${item.name} ",
                    color = Text_White
                )
            }

            // Income/Expense output
            Text(
                text = if (item.category == "Income")
                    " +£${item.amount}"
                else " -£${item.amount}",

                color = spendingColour
            )
        }

            //Category
            Text(
                text = "${item.category}",
                fontSize = 13.sp,
                color = Text_Grey,
            )
}





            }}}}}}


