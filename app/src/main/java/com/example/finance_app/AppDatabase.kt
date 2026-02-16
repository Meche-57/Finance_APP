package com.example.finance_app

import androidx.room.Database
import androidx.room.RoomDatabase

// database

@Database(entities = [Spending::class], version = 1)
abstract class AppDatabase: RoomDatabase(){
    abstract fun spendingDao(): SpendingDao
}


