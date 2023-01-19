package com.example.shoppingapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppingapp.News
import com.example.shoppingapp.R
import com.google.android.material.imageview.ShapeableImageView

class MainAdapter(private val newsList: ArrayList<News>): RecyclerView.Adapter<MainAdapter.MyViewHolder>(){

    private lateinit var mListener: onItemClickListener

    interface onItemClickListener {
        fun onItemClick(position: Int)

    }
    fun setOnItemClickListener(listener: onItemClickListener){
        mListener = listener
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_item,parent,false)
        return MyViewHolder(itemView,mListener)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = newsList[position]
        holder.Image.setImageResource(currentItem.Image)
        holder.tvHeading.text = currentItem.heading
        holder.tvPrice.text = currentItem.price.toString()
        holder.tvTitle.text = currentItem.news

    }

    override fun getItemCount(): Int {
        return newsList.size
    }

    class MyViewHolder(itemView: View, listener: onItemClickListener): RecyclerView.ViewHolder(itemView) {
        val Image: ShapeableImageView = itemView.findViewById(R.id.title_image)
        val tvHeading: TextView = itemView.findViewById(R.id.tvHeading)
        val tvPrice: TextView = itemView.findViewById(R.id.tvPrice)
        val tvTitle: TextView = itemView.findViewById(R.id.tvTitle)

        init {
            itemView.setOnClickListener{
                listener.onItemClick(adapterPosition)
            }
        }


    }





}