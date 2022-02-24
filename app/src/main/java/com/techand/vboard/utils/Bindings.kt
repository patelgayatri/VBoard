package com.techand.vboard.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.techand.vboard.data.models.Video

@BindingAdapter("app:image")
fun glide(view: ImageView, video: Video) {

    val url: String = if (video.thumbnails.size > 2)
        video.thumbnails[2].url
    else if (video.thumbnails.size > 1)
        video.thumbnails[1].url
    else if (video.thumbnails.isNotEmpty())
        video.thumbnails[0].url
    else
        NO_IMAGE

    if (url.isNotEmpty()) {
        Glide.with(view).load(url).into(view)
    }
}