package com.example.viewpager2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ViewPagerAdapter(var title:Array<String>) :
    RecyclerView.Adapter<ViewPagerAdapter.Pager2ViewHolder>()
{

    inner class Pager2ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val itemTitle:TextView=itemView.findViewById(R.id.tvTitle)
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewPagerAdapter.Pager2ViewHolder {
        return Pager2ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_page,parent,false))

    }

    override fun onBindViewHolder(holder: ViewPagerAdapter.Pager2ViewHolder, position: Int) {
       holder.itemTitle.text=title[position]
    }

    override fun getItemCount(): Int {
       return title.size
    }

}