package br.com.rocketseat.nlw.impulse.feedbackapp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import br.com.rocketseat.nlw.impulse.feedbackapp.databinding.ItemFeedbackCardBinding
import br.com.rocketseat.nlw.impulse.feedbackapp.ui.ui_utils.FeedbackCardItems

class FeedbackMainCardAdapter : ListAdapter<FeedbackCardItems, FeedbackMainCardAdapter.FeedbackMainCardHolder>(FeedbackMainDiffCallBack()) {

    var onItemClickListener: ((FeedbackCardItems) -> Unit)? = null

    inner class FeedbackMainCardHolder(private val binding: ItemFeedbackCardBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: FeedbackCardItems) {
            binding.txtFeedbackType.text = item.title
            binding.imgFeedbackType.setImageDrawable(ContextCompat.getDrawable(itemView.context, item.image))

            binding.root.setOnClickListener {
                onItemClickListener?.invoke(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedbackMainCardHolder {
        val layoutInflater: LayoutInflater = LayoutInflater.from(parent.context)
        val binding: ItemFeedbackCardBinding = ItemFeedbackCardBinding.inflate(layoutInflater, parent, false)
        return FeedbackMainCardHolder(binding)
    }

    override fun onBindViewHolder(holder: FeedbackMainCardHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class FeedbackMainDiffCallBack : DiffUtil.ItemCallback<FeedbackCardItems>() {
    override fun areItemsTheSame(oldItem: FeedbackCardItems, newItem: FeedbackCardItems): Boolean = oldItem.title == newItem.title

    override fun areContentsTheSame(oldItem: FeedbackCardItems, newItem: FeedbackCardItems): Boolean = oldItem == newItem
}