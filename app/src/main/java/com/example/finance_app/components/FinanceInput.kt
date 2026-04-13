package com.example.finance_app.components


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.finance_app.ui.theme.*



@Composable
fun FinanceInput(
    value: String,
    onValueChange: (String) -> Unit,
    label: String

    ) {

        Column {

            Text(text = label,
                fontSize = 12.sp,
                color = Text_White

            )


            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Box_Navy)

            ) {


                Column(modifier = Modifier.padding(15.dp)) {


                TextField(
                    value = value,
                    onValueChange = onValueChange,
                    colors = TextFieldDefaults.colors(
                        focusedTextColor = Text_White,
                        unfocusedTextColor = Text_White,

                        focusedContainerColor = Back_Navy,
                        unfocusedContainerColor = Back_Navy,

                    ),

                   // label = { Text(label, textAlign = TextAlign.Right)}


                )




                }
            }
}}