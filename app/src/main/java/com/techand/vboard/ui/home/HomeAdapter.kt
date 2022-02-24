package com.techand.vboard.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.techand.vboard.data.models.Content
import com.techand.vboard.databinding.ItemHomeBinding
import com.techand.vboard.home.HomeFragmentDirections
import javax.inject.Inject

class HomeAdapter @Inject constructor() :
    PagingDataAdapter<Content, HomeAdapter.ContentViewHolder>(VideoComparator) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ContentViewHolder(
            ItemHomeBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    override fun onBindViewHolder(holder: ContentViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
            holder.itemView.setOnClickListener { listItem ->
                val direction = HomeFragmentDirections
                    .actionHomeFragmentToHiltVideoPlayActivity(it.video.videoId)
                listItem.findNavController().navigate(direction)

            }
        }
    }

    inner class ContentViewHolder(private val binding: ItemHomeBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Content) = with(binding) {
            contents = item
        }
    }

    object VideoComparator : DiffUtil.ItemCallback<Content>() {
        override fun areItemsTheSame(oldItem: Content, newItem: Content) =
            oldItem.video == newItem.video

        override fun areContentsTheSame(oldItem: Content, newItem: Content) =
            oldItem.video.videoId == newItem.video.videoId
    }
}