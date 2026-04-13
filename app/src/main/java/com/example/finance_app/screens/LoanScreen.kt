package com.example.finance_app.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.finance_app.components.GoalsCard
import com.example.finance_app.components.LoanCalculator

@Preview(showBackground = true, showSystemUi = true)
@Composable

fun LoanScreen(){

    Column(modifier = Modifier.padding(15                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   .dp)){
        LoanCalculator()

        Spacer(modifier = Modifier.height(25.dp))



    }}