package com.example.todoapp.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.Model.Task
import com.example.todoapp.R

class TaskRecyclerViewAdapter(
    val context: Context,
    val onNoteClick : ((task:Task) -> Unit),
    val onDeleteIconClick : ((task:Task) -> Unit)
) : RecyclerView.Adapter<TaskRecyclerViewAdapter.ViewHolder>() {

    private val colTasks = ArrayList<Task>()
    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val taskTitle : TextView = itemView.findViewById(R.id.title)
        val taskDescription : TextView = itemView.findViewById(R.id.description)
        val taskDeleteBtn : ImageView = itemView.findViewById(R.id.deleteBtn)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.card_view,parent,false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.taskTitle.text = colTasks.get(position).title
        holder.taskDescription.text = colTasks.get(position).description

        holder.taskDeleteBtn.setOnClickListener{
            onDeleteIconClick(colTasks.get(position))
        }

        holder.itemView.setOnClickListener{
            onNoteClick((colTasks.get(position)))
        }

    }

    override fun getItemCount(): Int = colTasks.size

    fun updateList(newList : List<Task>){
        colTasks.clear()
        colTasks.addAll(newList)
        notifyDataSetChanged()
    }
}

