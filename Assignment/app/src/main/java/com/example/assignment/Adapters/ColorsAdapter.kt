package com.example.assignment.Adapters

import android.content.res.ColorStateList
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.BitmapDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment.DataClass.AvailableColor
import com.example.assignment.R
import de.hdodenhof.circleimageview.CircleImageView

class ColorAdapter(private val colors: List<AvailableColor>) : RecyclerView.Adapter<ColorAdapter.ColorViewHolder>() {

    class ColorViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val colorHolder: CircleImageView = itemView.findViewById(R.id.ivEachColor)

        fun bind(color: AvailableColor) {
            val drawable = createCircleDrawable(color.hex_code)
            colorHolder.setImageDrawable(drawable)
        }
        private fun createCircleDrawable(hexCode: String): BitmapDrawable {
            val bitmap = Bitmap.createBitmap(100, 100, Bitmap.Config.ARGB_8888)
            val canvas = Canvas(bitmap)
            val paint = Paint()
            paint.color = Color.parseColor(hexCode)
            paint.isAntiAlias = true
            canvas.drawCircle(50f, 50f, 50f, paint)
            return BitmapDrawable(itemView.resources, bitmap)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ColorViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.each_item_colors, parent, false)
        return ColorViewHolder(view)
    }

    override fun onBindViewHolder(holder: ColorViewHolder, position: Int) {
        val color = colors[position]
        holder.bind(color)
    }

    override fun getItemCount(): Int {
        return colors.size
    }
}