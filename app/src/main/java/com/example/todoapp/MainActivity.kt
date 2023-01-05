package com.example.todoapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.Adapter.TaskClickDeleteInterface
import com.example.todoapp.Adapter.TaskClickInterface
import com.example.todoapp.Adapter.TaskRecyclerViewAdapter
import com.example.todoapp.Model.Task
import com.example.todoapp.ViewModel.TaskViewModel
import com.example.todoapp.databinding.ActivityMainBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity(), TaskClickInterface, TaskClickDeleteInterface {

    private lateinit var mainBinding: ActivityMainBinding
    lateinit var viewModel : TaskViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        val view = mainBinding.root
        setContentView(view)

        mainBinding.recyclerView.layoutManager = LinearLayoutManager(this)

        val taskRecyclerViewAdapter = TaskRecyclerViewAdapter(this,this,this)
        mainBinding.recyclerView.adapter = taskRecyclerViewAdapter
        viewModel = ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(TaskViewModel::class.java)
        viewModel.colTask.observe(this, Observer { list->
            list?.let {
                taskRecyclerViewAdapter.updateList(it)
            }

        })

        mainBinding.floatingActionButton.setOnClickListener{
            val intent = Intent(this@MainActivity,InsertActivity::class.java)
            startActivity(intent)
            this.finish()
        }

    }

    override fun onNoteClick(task: Task) {
        val intent = Intent(this@MainActivity,InsertActivity::class.java)
        intent.putExtra("TaskType","Edit")
        intent.putExtra("TaskTitle",task.title)
        intent.putExtra("TaskDescription",task.description)
        intent.putExtra("TaskID",task.id)
        startActivity(intent)
        this.finish()

    }

    override fun onDeleteIconClick(task: Task) {
        viewModel.deleteTask(task)
        Toast.makeText(this,"${task.title} Deleted", Toast.LENGTH_SHORT).show()
    }
}