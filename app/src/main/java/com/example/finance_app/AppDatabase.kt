package com.example.finance_app

import androidx.room.Database
import androidx.room.RoomDatabase

// database

@Database(entities = [Spending::class, Goals::class , Budget::class],  version = 3) // update version changes
abstract class AppDatabase: RoomDatabase(){
    abstract fun spendingDao(): SpendingDao
    abstract fun goalsDao(): GoalsDao
    abstract fun budgetDao(): BudgetDao

    companion object



}


