package com.example.todoapp.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.todoapp.DB.TasksDatabase
import com.example.todoapp.Model.Task
import com.example.todoapp.Respository.TaskRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TaskViewModel(application: Application) : AndroidViewModel(application) {

    val colTask: LiveData<List<Task>>
    val repository:TaskRepository

    init{
        val dao = TasksDatabase.getDatabase(application).getTasksDao()
        repository = TaskRepository(dao)
        colTask = repository.colTask
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
}