package com.example.finance_app.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.sp
import com.example.finance_app.ui.theme.ExpenseRed
import com.example.finance_app.ui.theme.IncomeGreen
import com.example.finance_app.ui.theme.Text_White


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


        Row ( modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween) {

            Text(
                text = " ${item.name} ",
                color = Text_White
            )

            Text(
                text = if (item.category == "Income")
                    " +£${item.amount}"
                else " -£${item.amount}",

                color = spendingColour
            )
        }
            Text(
                text = "${item.category}",
                fontSize = 13.sp,
                color = spendingColour
            )
}





            }}}}}


