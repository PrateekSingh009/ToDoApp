package com.example.todoapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.todoapp.MainActivity.Companion.EDIT
import com.example.todoapp.MainActivity.Companion.TASK_DESCRIPTION
import com.example.todoapp.MainActivity.Companion.TASK_ID
import com.example.todoapp.MainActivity.Companion.TASK_TITLE
import com.example.todoapp.MainActivity.Companion.TASK_TYPE
import com.example.todoapp.Model.Task
import com.example.todoapp.ViewModel.TaskViewModel
import com.example.todoapp.databinding.ActivityInsertBinding

class InsertActivity : AppCompatActivity() {

    companion object {
        private const val UPDATE_NOTE = "Update Note"
        private const val SAVE_NOTE = "Save Note"
        private const val TASK_UPDATED = "Task Updated"
        private const val TASK_SAVED = "Task Saved"
    }

    private val insertActivityBinding: ActivityInsertBinding by lazy {
        ActivityInsertBinding.inflate(
            layoutInflater
        )
    }
    private val viewModel: TaskViewModel by lazy {
        ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        )[TaskViewModel::class.java]
    }

    private var taskID = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(insertActivityBinding.root)
        setUpViews()
        setUpListeners()
    }

    private fun setUpListeners() {
        insertActivityBinding.apply {
            saveBtn.setOnClickListener {
                val task = Task(titleEdit.text.toString(), descriptionEdit.text.toString())
                if (intent.getStringExtra(TASK_TYPE).equals(EDIT)) {
                    if (task.title.isNotEmpty() && task.description.isNotEmpty()) {
                        task.id = taskID
                        viewModel.updateTask(task)
                        Toast.makeText(this@InsertActivity, TASK_UPDATED, Toast.LENGTH_SHORT).show()
                    }
                } else {
                    if (task.title.isNotEmpty() && task.description.isNotEmpty()) {
                        viewModel.addTask(task)
                        Toast.makeText(this@InsertActivity, TASK_SAVED, Toast.LENGTH_SHORT).show()
                    }
                }
                startActivity(Intent(applicationContext, MainActivity::class.java))
            }
        }
    }

    private fun setUpViews() {
        insertActivityBinding.let {
            intent.apply {
                if (getStringExtra(TASK_TYPE).equals(EDIT)) {
                    taskID = getIntExtra(TASK_ID, -1)
                    it.saveBtn.text = UPDATE_NOTE
                    it.titleEdit.setText(getStringExtra(TASK_TITLE))
                    it.descriptionEdit.setText(getStringExtra(TASK_DESCRIPTION))
                } else {
                    it.saveBtn.text = SAVE_NOTE
                }
            }
        }
        viewModel
    }
}