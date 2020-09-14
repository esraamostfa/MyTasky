package com.esraamostfa.mytasky.ui.tasks

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.esraamostfa.mytasky.R
import com.esraamostfa.mytasky.model.Task
import kotlinx.android.synthetic.main.task_item_card.view.*

class TasksRecyclerviewAdapter() :
    RecyclerView.Adapter<TasksRecyclerviewAdapter.TasksViewHolder>() {

    private var tasks = emptyList<Task>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TasksViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.task_item_card, parent, false)
        return TasksViewHolder(view)
    }

    override fun onBindViewHolder(holder: TasksViewHolder, position: Int) {
        holder.bind(tasks[position])
    }

    override fun getItemCount(): Int = tasks.size

    internal fun updateTasks(tasks: MutableList<Task>) {
        this.tasks = tasks
        notifyDataSetChanged()
    }

    class TasksViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {
        override fun onClick(p0: View?) {
            TODO("Not yet implemented")
        }

        private lateinit var task: Task
        fun bind(task: Task) {
            this.task = task
            itemView.task_item.text = task.Title
        }
    }
}