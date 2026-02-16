package com.example.finance_app
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Delete

@Dao //Simple SQL
interface SpendingDao {

    @Query("Select * from Spending")
    suspend fun getAll(): List<Spending>

    @Insert
    suspend fun insertSpending(spending: Spending)

    @Delete
    suspend fun deleteSpending(spending: Spending)

    @Query("SELECT * FROM Spending where category LIKE :categoryName")
    suspend fun findCategory(categoryName: String): List<Spending>


}