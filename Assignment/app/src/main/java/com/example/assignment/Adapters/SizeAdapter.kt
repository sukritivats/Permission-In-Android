import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment.R

class SizeAdapter(
    private val sizes: List<String>,
    private val onItemClick: (String) -> Unit
) : RecyclerView.Adapter<SizeAdapter.SizeViewHolder>() {

    private var selectedPosition: Int = RecyclerView.NO_POSITION
    inner class SizeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val sizeTextView: TextView = itemView.findViewById(R.id.tvEachSize)

        fun bind(size: String, isSelected: Boolean) {
            sizeTextView.text = size
            if (isSelected) {
                sizeTextView.setTextColor(Color.RED)
            } else {
                sizeTextView.setTextColor(Color.BLACK)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SizeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.each_item_size, parent, false)
        return SizeViewHolder(view)
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onBindViewHolder(holder: SizeViewHolder, position: Int) {
        val size = sizes[position]
        holder.bind(size, position == selectedPosition)

        holder.itemView.setOnClickListener {
            selectedPosition = holder.adapterPosition
            notifyDataSetChanged()
            onItemClick(size)
        }
    }

    override fun getItemCount(): Int {
        return sizes.size
    }
    @SuppressLint("NotifyDataSetChanged")
    fun setSelectedPosition(position: Int) {
        selectedPosition = position
        notifyDataSetChanged()
    }
}
