package com.example.finance_app

import androidx.room.Dao

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query



@Dao

interface BudgetDao {


    @Query("Select * from Budget WHERE id = 1") // Theres only one budget
    suspend fun getAll(): List<Budget>

    @Insert(onConflict = OnConflictStrategy.REPLACE) // replaces old budget with new
    suspend fun insertBudget(budget: Budget)


}
