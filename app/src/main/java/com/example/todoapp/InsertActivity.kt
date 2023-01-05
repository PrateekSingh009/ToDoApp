package com.example.todoapp

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.todoapp.Model.Task
import com.example.todoapp.ViewModel.TaskViewModel
import com.example.todoapp.databinding.ActivityInsertBinding
import com.example.todoapp.databinding.ActivityMainBinding

class InsertActivity : AppCompatActivity() {

    private lateinit var insertBinding: ActivityInsertBinding
    lateinit var viewModel : TaskViewModel
    var taskID = -1


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        insertBinding = ActivityInsertBinding.inflate(layoutInflater)
        val view = insertBinding.root
        setContentView(view)

        viewModel = ViewModelProvider(this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(TaskViewModel::class.java)

        val taskType = intent.getStringExtra("TaskType")
        if(taskType.equals("Edit")){
            val taskTitle = intent.getStringExtra("TaskTitle")
            val taskDescription = intent.getStringExtra("TaskDescription")
            taskID = intent.getIntExtra("TaskID",-1)
            insertBinding.saveBtn.text = "Update Note"
            insertBinding.titleEdit.setText(taskTitle)
            insertBinding.descriptionEdit.setText(taskDescription)

        }else{
            insertBinding.saveBtn.text = "Save Note"
        }

        insertBinding.saveBtn.setOnClickListener{
            val taskTitle = insertBinding.titleEdit.text.toString()
            val taskDesc = insertBinding.descriptionEdit.text.toString()

            if(taskType.equals("Edit")){
                if(taskTitle.isNotEmpty() && taskDesc.isNotEmpty()) {
                    val updateTask = Task(taskTitle, taskDesc)
                    updateTask.id = taskID
                    viewModel.updateTask(updateTask)
                    Toast.makeText(this, "Task Updated", Toast.LENGTH_SHORT).show()
                }
            }else{
                if(taskTitle.isNotEmpty() && taskDesc.isNotEmpty())
                {
                    viewModel.addTask(Task(taskTitle,taskDesc))
                    Toast.makeText(this, "Task Saved", Toast.LENGTH_SHORT).show()
                }
            }
            startActivity(Intent(applicationContext,MainActivity::class.java))
        }

    }
}