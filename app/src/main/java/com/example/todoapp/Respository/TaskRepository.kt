package com.example.todoapp.Respository

import com.example.todoapp.DAO.TasksDao
import com.example.todoapp.Model.Task

class TaskRepository(private val tasksDao : TasksDao ) {

    suspend fun insert(task: Task){
        tasksDao.insert(task)
    }

    suspend fun delete(task: Task){
        tasksDao.delete(task)
    }

    suspend fun update(task: Task){
        tasksDao.update(task)
    }

    fun getAllTask() = tasksDao.getAllTasks()
}