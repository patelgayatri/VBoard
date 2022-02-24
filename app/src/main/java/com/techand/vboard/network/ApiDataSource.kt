package com.techand.vboard.network

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.techand.vboard.data.models.Content
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

const val API_INDEX = 1

class ApiDataSource @Inject constructor(private val apiService: VideosApi) :
    PagingSource<Int, Content>() {


    override fun getRefreshKey(state: PagingState<Int, Content>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Content> {
        val position = params.key ?: API_INDEX
        return try {

            val response = apiService.fetchVideos(position)

            val listing = response.contents

            LoadResult.Page(
                listing, if (position == API_INDEX) null else position - 1,if (listing.isEmpty()) null else position + 1,
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }
}