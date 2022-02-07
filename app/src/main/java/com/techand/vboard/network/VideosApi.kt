package com.techand.vboard.network

import com.techand.vboard.data.models.FeedResult
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface VideosApi {

    @Headers(
        apikey,
        apiHost
    )

    @GET("trending")
    suspend fun fetchVideos(@Query("page") query: Int): FeedResult
}
