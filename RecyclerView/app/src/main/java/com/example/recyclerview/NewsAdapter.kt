package com.example.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.imageview.ShapeableImageView

class NewsAdapter(private val newsList:ArrayList<Data>): RecyclerView.Adapter<NewsAdapter.MyViewHolder>()
{
    private lateinit var mListener:onItemClickListener
    interface onItemClickListener{
        fun onItemClick(position: Int)

    }
    fun setonItemClickListener(listener: onItemClickListener)
    {
        mListener=listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsAdapter.MyViewHolder {
        val itemView =LayoutInflater.from(parent.context).inflate(R.layout.list_item,parent,false)
        return MyViewHolder(itemView,mListener)
    }

    override fun onBindViewHolder(holder: NewsAdapter.MyViewHolder, position: Int) {
        val currentItem = newsList[position]
        holder.image.setImageResource(currentItem.titleImage) // titleImage and heading from data class
        holder.heading.text=currentItem.heading

    }

    override fun getItemCount(): Int {
        return newsList.size
    }

    class MyViewHolder(itemView: View,listener: onItemClickListener):RecyclerView.ViewHolder(itemView) {
         val image:ShapeableImageView = itemView.findViewById(R.id.title_image)
         val heading:TextView = itemView.findViewById(R.id.heading)

         init{
             itemView.setOnClickListener {
                 listener.onItemClick(adapterPosition)
             }
         }
    }

}