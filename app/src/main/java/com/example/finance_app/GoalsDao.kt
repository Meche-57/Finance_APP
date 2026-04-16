package com.example.finance_app
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Delete
import androidx.room.Update

@Dao

interface GoalsDao {

    @Query("Select * from Goals")
    suspend fun getAll(): List<Goals>

    @Insert
    suspend fun insertGoal(goals: Goals)

    @Delete
    suspend fun deleteGoal(goals: Goals)

    @Query("SELECT * FROM Goals where title LIKE :titleName")
    suspend fun findTitle(titleName: String): List<Goals>

    @Update
    suspend fun updateGoal(goals: Goals)

}



