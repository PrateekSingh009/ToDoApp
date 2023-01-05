package com.example.todoapp.DB

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.todoapp.DAO.TasksDao
import com.example.todoapp.Model.Task

@Database(entities = arrayOf(Task::class), version = 1, exportSchema = false)
abstract class TasksDatabase : RoomDatabase() {

    abstract fun getTasksDao(): TasksDao

    companion object {

        private const val TASK_DATABASE = "Task_Database"

        @Volatile
        private var databaseInstance: TasksDatabase? = null

        fun getDatabase(context: Context): TasksDatabase {

            return databaseInstance ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TasksDatabase::class.java,
                    TASK_DATABASE
                ).build()
                databaseInstance = instance
                instance
            }
        }
    }
}