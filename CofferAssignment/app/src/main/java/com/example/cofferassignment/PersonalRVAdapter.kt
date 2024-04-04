package com.example.cofferassignment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.navigation.NavigationBarItemView

class PersonalRVAdapter(private val item:ArrayList<MyData>):RecyclerView.Adapter<PersonalRVAdapter.MyViewHolder>() {
    class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        val dPHolder:TextView=itemView.findViewById(R.id.tvDP)
        val nameHolder:TextView=itemView.findViewById(R.id.tvUserName)

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PersonalRVAdapter.MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.each_item_personal_rv,parent,false))
    }

    override fun onBindViewHolder(holder: PersonalRVAdapter.MyViewHolder, position: Int) {
        holder.nameHolder.setText(item[position].name)
        holder.dPHolder.setText(item[position].dp)
    }

    override fun getItemCount(): Int {
        return  item.size
    }

}