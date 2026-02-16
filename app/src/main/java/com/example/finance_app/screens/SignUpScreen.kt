package com.example.finance_app.screens

import android.R
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.finance_app.ui.theme.Back_Navy
import com.example.finance_app.ui.theme.Text_White
import java.nio.file.WatchEvent


@Composable
@Preview

fun SignUpScreen() {

    Column(modifier = Modifier
        .fillMaxSize()
        .background(Back_Navy)
        .padding(50.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top



    ){
        Text(text = "Sign Up", fontSize = 32.sp, color = Text_White)
        Text(text = "Create an account to begin :)", fontSize = 16.sp, color = Text_White,
            modifier = Modifier.padding(top = 10.dp))





    }





}
