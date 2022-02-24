package com.techand.vboard.network

import com.techand.vboard.data.models.FeedResult
import retrofit2.http.GET
import retrofit2.http.Query

interface VideosApi {


    @GET("trending")
    suspend fun fetchVideos(@Query("page") query: Int): FeedResult


    @GET("video/related")
    suspend fun fetchRelated(@Query("id") id: String): FeedResult
}
