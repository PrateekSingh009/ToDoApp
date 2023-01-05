package com.example.todoapp.Respository

import androidx.lifecycle.LiveData
import com.example.todoapp.DAO.TasksDao
import com.example.todoapp.Model.Task

class TaskRepository(private val tasksDao : TasksDao ) {

    val colTask : LiveData<List<Task>> = tasksDao.getAllTasks()

    suspend fun insert(task: Task){
        tasksDao.insert(task)
    }

    suspend fun delete(task: Task){
        tasksDao.delete(task)
    }

    suspend fun update(task: Task){
        tasksDao.update(task)
    }



}