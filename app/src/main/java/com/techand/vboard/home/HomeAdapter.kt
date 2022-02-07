package com.techand.vboard.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.techand.vboard.data.models.Content
import com.techand.vboard.databinding.ItemHomeBinding
import javax.inject.Inject

class HomeAdapter @Inject constructor() :
    PagingDataAdapter<Content, HomeAdapter.AwemeViewHolder>(AwemeComparator) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        AwemeViewHolder(
            ItemHomeBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    override fun onBindViewHolder(holder: AwemeViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    inner class AwemeViewHolder(private val binding: ItemHomeBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Content) = with(binding) {
            contents = item
        }
    }

    object AwemeComparator : DiffUtil.ItemCallback<Content>() {
        override fun areItemsTheSame(oldItem: Content, newItem: Content) =
            oldItem.video == newItem.video

        override fun areContentsTheSame(oldItem: Content, newItem: Content) =
            oldItem.video.videoId == newItem.video.videoId
    }
}