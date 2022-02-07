package com.techand.vboard.data.models

import android.icu.text.CompactDecimalFormat
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import java.util.*

data class Video(
    val channelId: String = "",
    val channelName: String = "",
    val lengthText: String = "",
    val publishedTimeText: String = "",
    val thumbnails: List<Thumbnail> = listOf(),
    val title: String = "",
    val videoId: String = "",
    val viewCountText: String = ""
) {
    @RequiresApi(Build.VERSION_CODES.N)
    fun getView(): String {
        val viewNumbers = viewCountText.replace(" views", "").replace(",", "").trim()
        Log.e("===", viewNumbers)
        val b = Integer.parseInt(viewNumbers)
        Log.e("===", "$b")
        val formattedNumber = CompactDecimalFormat.getInstance(
            Locale.getDefault(),
            CompactDecimalFormat.CompactStyle.SHORT
        ).format(b)
        return " . $formattedNumber . "
    }
}