package com.example.todoapp.DAO

import androidx.annotation.RequiresPermission.Read
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.todoapp.Model.Task


@Dao
interface TasksDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE) //On insertion data with the same id will be ignored
    suspend fun insert(task : Task)

    @Update
    suspend fun update(task : Task)

    @Delete
    suspend fun delete(task : Task)

    @Query("Select * from tableTask order by id ASC") //ordering data by its id in ascending manner
    fun getAllTasks() : LiveData<List<Task>>
}