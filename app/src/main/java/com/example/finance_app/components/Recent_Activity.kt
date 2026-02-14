package com.example.finance_app.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun Recent_Activity() {

    val activitys = listOf("Netflix", "Lidl", "Greggs")

    Column(modifier = Modifier.padding(20.dp)) {

        Text(text = "Recent Activity:")

    for (activity in activitys) {
        Text(
            text = activity,
            modifier = Modifier.padding(vertical = 4.dp)
        )

    }
}
}