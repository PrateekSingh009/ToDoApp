package com.example.todoapp.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoapp.DB.TasksDatabase
import com.example.todoapp.Model.Task
import com.example.todoapp.Respository.TaskRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TaskViewModel(application: Application) : AndroidViewModel(application) {

    private val repository:TaskRepository

    init{
        val dao = TasksDatabase.getDatabase(application).getTasksDao()
        repository = TaskRepository(dao)
    }

    fun deleteTask(task: Task) = viewModelScope.launch(Dispatchers.IO){
        repository.delete(task)
    }

    fun updateTask(task: Task) = viewModelScope.launch(Dispatchers.IO){
        repository.update(task)
    }

    fun addTask(task: Task) = viewModelScope.launch(Dispatchers.IO){
        repository.insert(task)
    }

    fun getAllTask() = repository.getAllTask()
}