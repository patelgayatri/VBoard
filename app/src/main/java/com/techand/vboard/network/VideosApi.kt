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
//    @GET("search/top?query=top&sort=1")
//    suspend fun fetchVideos(@Query("offset") query: Int): VideoResult

    //jobs@sentia.com.au

    @GET("trending")
    suspend fun fetchVideos(@Query("page") query: Int): FeedResult
}
