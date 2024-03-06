package com.example.practice.apps

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.practice.R
import com.google.android.material.imageview.ShapeableImageView

class AppsAdapterViewPager2(private var image:Array<Int>): RecyclerView.Adapter<AppsAdapterViewPager2.Pager2ViewHolder>()
{
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): Pager2ViewHolder {
        return Pager2ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.apps_viewpager2_item,parent,false))
    }

    override fun getItemCount(): Int {
       return image.size
    }

    override fun onBindViewHolder(holder: Pager2ViewHolder, position: Int) {
        val currentMsg = image[position]
        holder.imageHolder.setImageResource(currentMsg)
    }

    inner class Pager2ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val imageHolder: ShapeableImageView =itemView.findViewById(R.id.view_pager_img)
    }

}



