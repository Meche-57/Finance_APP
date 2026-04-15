package com.example.finance_app
import androidx.room.PrimaryKey
import androidx.room.Entity

@Entity

data class Goals(
    // Holds goals in data base
    @PrimaryKey(autoGenerate = true) var id: Int = 0,

    val title: String,
    val current: Double,
    val goal: Double,

)

