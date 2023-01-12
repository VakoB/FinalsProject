package com.example.shoppingapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppingapp.Task
import com.example.shoppingapp.databinding.RecyclerviewitemItemBinding
import com.example.shoppingapp.objects.TaskList.taskList

class MainAdapter:RecyclerView.Adapter<MainAdapter.MainViewHolder>() {
    inner class MainViewHolder(val itemBinding: RecyclerviewitemItemBinding)
        :RecyclerView.ViewHolder(itemBinding.root){
            fun bindItem(task: Task){
                itemBinding.titleTv.text = task.title
                itemBinding.timeTv.text = task.timestamp

            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(RecyclerviewitemItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))        //parent and false aren't necessary
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val task = taskList[position]
        holder.bindItem(task)
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }
}