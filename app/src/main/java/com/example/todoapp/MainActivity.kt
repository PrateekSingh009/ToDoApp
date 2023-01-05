package com.example.todoapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todoapp.Adapter.TaskRecyclerViewAdapter
import com.example.todoapp.Model.Task
import com.example.todoapp.ViewModel.TaskViewModel
import com.example.todoapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    companion object {
        const val TASK_TYPE = "TaskType"
        const val TASK_TITLE= "TaskTitle"
        const val TASK_DESCRIPTION = "TaskDescription"
        const val TASK_ID = "TaskID"
        const val EDIT = "Edit"

    }
    private val mainBinding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val linearLayoutManager: LinearLayoutManager by lazy { LinearLayoutManager(this) }
    private val taskRecyclerViewAdapter : TaskRecyclerViewAdapter by lazy{ TaskRecyclerViewAdapter(::onNoteClick, ::onDeleteIconClick) }
    lateinit var viewModel: TaskViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mainBinding.root)
        setUpViews()
        setUpObservers()
        setUpListeners()
    }

    private fun setUpViews() {
        mainBinding.recyclerView.apply {
            layoutManager = linearLayoutManager
            adapter = taskRecyclerViewAdapter
        }

        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        ).get(TaskViewModel::class.java)
    }

    private fun setUpObservers(){
        viewModel.getAllTask().observe(this) { list ->
            list?.let {
                taskRecyclerViewAdapter.updateList(it)
            }
        }
    }

    private fun setUpListeners(){
        mainBinding.floatingActionButton.setOnClickListener {
            startActivity(Intent(this@MainActivity, InsertActivity::class.java))
            this.finish()
        }
    }

    private fun onNoteClick(task: Task) {
        with (Intent(this@MainActivity, InsertActivity::class.java) ) {
            putExtra(TASK_TYPE, EDIT)
            putExtra(TASK_TITLE, task.title)
            putExtra(TASK_DESCRIPTION, task.description)
            putExtra(TASK_ID, task.id)
            startActivity(this)
        }
        this.finish()
    }

    private fun onDeleteIconClick(task: Task) {
        viewModel.deleteTask(task)
        Toast.makeText(this, "${task.title} Deleted", Toast.LENGTH_SHORT).show()
    }
}