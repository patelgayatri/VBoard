package com.techand.vboard.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("app:image")
fun glide(view: ImageView, url: String?) {
    if (!url.isNullOrEmpty()) {
        Glide.with(view).load(url).into(view)
    }
}