package com.example.finance_app

import androidx.room.PrimaryKey
import androidx.room.Entity


@Entity // Table

data class Spending(
    // data class to hold data for transactions

    // creates a primary key automatically when a new transaction is created
    @PrimaryKey(autoGenerate = true) var id: Int = 0,

    val name: String?,
    val amount: Double,
    val date: String?,
    val category: String?




)
