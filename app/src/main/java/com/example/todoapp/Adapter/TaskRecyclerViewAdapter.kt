package com.example.todoapp.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.Model.Task
import com.example.todoapp.databinding.CardViewBinding

class TaskRecyclerViewAdapter(
    val onNoteClick: ((task: Task) -> Unit),
    val onDeleteIconClick: ((task: Task) -> Unit)
) : RecyclerView.Adapter<TaskRecyclerViewAdapter.ViewHolder>() {

    private val colTasks = ArrayList<Task>()

    inner class ViewHolder(private val binding: CardViewBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(position: Int) {
            binding.apply {
                title.text = colTasks[position].title
                description.text = colTasks[position].description
                deleteBtn.setOnClickListener {
                    onDeleteIconClick(colTasks[position])
                }
                itemView.setOnClickListener {
                    onNoteClick((colTasks[position]))
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(CardViewBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(position)

    override fun getItemCount(): Int = colTasks.size

    fun updateList(newList: List<Task>) {
        colTasks.clear()
        colTasks.addAll(newList)
        notifyDataSetChanged()
    }
}

