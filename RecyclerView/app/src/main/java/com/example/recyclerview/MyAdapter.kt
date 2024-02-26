//package com.example.recyclerview
//
//import android.app.Activity
//import android.content.Context
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.ImageButton
//import android.widget.ImageView
//import android.widget.TextView
//import androidx.appcompat.view.menu.MenuView.ItemView
//import androidx.recyclerview.widget.RecyclerView
//
//// adapter --> connects UI with data sources
//class MyAdapter(val context: Activity, val dataList:List<Data>):
//    RecyclerView.Adapter<MyAdapter.MyViewHolder>()
//{
//    class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
//        val image: ImageView
//        val title:TextView
//        val play:ImageButton
//        val pause:ImageButton
//
//        init{
//            image= itemView.findViewById(R.id.image)
//            title= itemView.findViewById(R.id.title)
//            play= itemView.findViewById(R.id.play)
//            pause= itemView.findViewById(R.id.pause)
//        }
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
//        // called when RV needs a new ViewHolder of given type to represent an item
//        // create the view in case layout manager fails to create the view for data
//        val itemView = LayoutInflater.from(context).inflate(R.layout.each_item,parent, false)
//        return MyViewHolder(itemView)
//    }
//
//    override fun getItemCount(): Int {
//        //return size of list
//        return dataList.size
//    }
//
//    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
//        // it is called by recycler view to
//        // display data at specified position
//        // populate the data into view
//        TODO("Not yet implemented")
//    }
//
//
//}