package com.techand.vboard.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.techand.vboard.data.models.Content
import com.techand.vboard.network.ApiDataSource
import com.techand.vboard.network.VideosApi
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class VideosRepository @Inject constructor(private val service: VideosApi) {

    fun fetchPosts(): Flow<PagingData<Content>> {
        return Pager(
            PagingConfig(pageSize = 10, enablePlaceholders = false)
        ) { ApiDataSource(service) }.flow
    }
}
