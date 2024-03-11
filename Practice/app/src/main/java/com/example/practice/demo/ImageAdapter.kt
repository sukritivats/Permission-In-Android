//package com.example.practice.demo
//
//import android.net.Uri
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import androidx.recyclerview.widget.RecyclerView
//import com.bumptech.glide.Glide
//import com.example.practice.R
//
//class ImageAdapter(private val images: List<Uri>) :
//    RecyclerView.Adapter<ImageAdapter.ImageViewHolder>() {
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
//        val view = LayoutInflater.from(parent.context)
//            .inflate(R.layout.item_image, parent, false)
//        return ImageViewHolder(view)
//    }
//
//    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
//        holder.bind(images[position])
//    }
//
//    override fun getItemCount(): Int {
//        return images.size
//    }
//
//    inner class ImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        fun bind(imageUri: Uri) {
//            Glide.with(itemView)
//                .load(imageUri)
//                .into(itemView.imageView)
//        }
//    }
//}