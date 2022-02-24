package com.techand.vboard.data.models

import android.icu.text.CompactDecimalFormat
import android.os.Build
import android.os.Parcelable
import android.util.Log
import androidx.annotation.RequiresApi
import com.techand.vboard.utils.TAG
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue
import java.util.*

@Parcelize
data class Video(
    val channelId: String = "",
    val channelName: String = "",
    val lengthText: String = "",
    val publishedTimeText: String = "",
    val thumbnails: @RawValue List<Thumbnail> = listOf(),
    val title: String = "",
    val videoId: String = "",
    val viewCountText: String = ""
) : Parcelable {
    @RequiresApi(Build.VERSION_CODES.N)
    fun getView(): String {
        val viewNumbers = viewCountText.replace(" views", "").replace(",", "").trim()
        var formattedNumber = ""
        try {
            val b = Integer.parseInt(viewNumbers)
            formattedNumber = CompactDecimalFormat.getInstance(
                Locale.getDefault(),
                CompactDecimalFormat.CompactStyle.SHORT
            ).format(b)
        } catch (e: Exception) {
            Log.e(TAG, e.message.toString())
        }

        return "  $formattedNumber views  "
    }
}