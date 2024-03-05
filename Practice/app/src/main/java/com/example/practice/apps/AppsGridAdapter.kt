package com.example.practice

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

interface OnItemClickListener {
    fun onItemClick(position: Int)
}

class AppsGridAdapter(private val appsList: ArrayList<AppsGridData>) :
    RecyclerView.Adapter<AppsGridAdapter.AppViewHolder>()
{
    class AppViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        val imageView: ImageView = itemView.findViewById(R.id.imageView)
        val textView: TextView = itemView.findViewById(R.id.tvGrid)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AppViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.apps_grid, parent, false)
        return AppViewHolder(view)
    }



    override fun getItemCount(): Int {
        return appsList.size
    }

    override fun onBindViewHolder(holder: AppViewHolder, position: Int) {
        val apps = appsList[position]
        holder.imageView.setImageResource(apps.image)
        holder.textView.text = apps.name
    }

}