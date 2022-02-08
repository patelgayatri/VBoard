package com.techand.vboard.home

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.techand.vboard.data.models.Content
import com.techand.vboard.databinding.ItemHomeBinding
import com.techand.vboard.videoplay.VideoPlayActivity
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
                val intent = Intent(listItem.context, VideoPlayActivity::class.java)
                intent.putExtra("videoID",it.video.videoId)
                listItem.context.startActivity(intent)
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