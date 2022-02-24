package com.techand.vboard.ui.videoplay

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.techand.vboard.data.models.Content
import com.techand.vboard.databinding.ItemHomeBinding

class RelatedAdapter(
    private val values: List<Content>
) : RecyclerView.Adapter<RelatedAdapter.ContentViewHolder>() {


    override fun onBindViewHolder(holder: ContentViewHolder, position: Int) {
        val item = values[position]
        item.let {
            holder.bind(it)
            holder.itemView.setOnClickListener { listItem ->
                val direction = RelatedFragmentDirections
                    .actionRelatedFragmentToVideoPlayActivity(it.video.videoId)
                listItem.findNavController().navigate(direction)

            }
        }
    }

    override fun getItemCount(): Int = values.size

    inner class ContentViewHolder(private val binding: ItemHomeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Content) = with(binding) {
            contents = item
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContentViewHolder =
        ContentViewHolder(
            ItemHomeBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

}