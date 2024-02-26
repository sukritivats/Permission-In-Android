package com.example.practice

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.imageview.ShapeableImageView
import de.hdodenhof.circleimageview.CircleImageView
import java.util.Collections

class MessageAdapter(private val messageList: ArrayList<MessageData>)
    :RecyclerView.Adapter<MessageAdapter.MyViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageAdapter.MyViewHolder {
        val msgView = LayoutInflater.from(parent.context).inflate(R.layout.message_each_item,parent,false)
        return MyViewHolder(msgView)
    }
    override fun onBindViewHolder(holder: MessageAdapter.MyViewHolder, position: Int) {
        val currentMsg = messageList[position]
        holder.imageHolder.setImageResource(currentMsg.imageData)
        holder.aboutHolder.text= currentMsg.aboutData
        holder.deleteIcon.setImageResource(R.drawable.baseline_delete_24)
    }
    override fun getItemCount(): Int {
        return messageList.size
    }
    fun swapItems(fromPosition: Int, toPosition: Int) {
        if (fromPosition != RecyclerView.NO_POSITION && toPosition != RecyclerView.NO_POSITION) {
            Collections.swap(messageList, fromPosition, toPosition)
            notifyItemMoved(fromPosition, toPosition)
        }
    }
   inner class MyViewHolder(msgView: View):RecyclerView.ViewHolder(msgView) {
        val imageHolder: CircleImageView = msgView.findViewById(R.id.dp)
        val aboutHolder: TextView = msgView.findViewById(R.id.title)
        val deleteIcon:ImageView=msgView.findViewById(R.id.delete)

        init {
            deleteIcon.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val builder = AlertDialog.Builder(itemView.context)
                    builder.setTitle("Alert Dialog")
                        .setMessage("Are you sure you want to delete this message?")
                    builder.setCancelable(true)
                    builder.setPositiveButton("Yes") { dialog, _ ->
                        messageList.removeAt(position)
                        notifyItemRemoved(position)
                    }
                    builder.setNegativeButton("Cancel") { dialog, _ ->
                        dialog.dismiss()
                    }
                    builder.create().show()
                }
            }
        }
    }
}
