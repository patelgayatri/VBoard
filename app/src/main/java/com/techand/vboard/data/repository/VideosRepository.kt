package com.techand.vboard.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.techand.vboard.data.models.Content
import com.techand.vboard.network.ApiDataSource
import com.techand.vboard.network.VideosApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class VideosRepository @Inject constructor(private val service: VideosApi) {

    fun getTrending(): Flow<PagingData<Content>> {
        return Pager(
            PagingConfig(pageSize = 10, enablePlaceholders = false)
        ) { ApiDataSource(service) }.flow
    }
    suspend fun getRelatedVideos(id:String): Flow<Result<List<Content>>> {
        val result= flow {
            emit(Result.success(service.fetchRelated(id).contents))
        }.catch {
            emit(Result.failure(RuntimeException(it.message ?: "Error")))
        }
       return result.map {
            if (it.isSuccess)
                Result.success(it.getOrNull()!!)
            else
                Result.failure(it.exceptionOrNull()!!)
        }
    }
}
