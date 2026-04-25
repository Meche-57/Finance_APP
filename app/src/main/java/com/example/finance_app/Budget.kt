package com.example.finance_app

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity

data class Budget(

    @PrimaryKey(autoGenerate = true) var id: Int = 1,

    val budgetGoal: Double

    )
