package com.techand.vboard.ui.home

import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.techand.vboard.data.models.Content
import com.techand.vboard.data.repository.VideosRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach

class HomeViewModel(private val repository: VideosRepository) : ViewModel() {
     val loader = MutableLiveData<Boolean>()

    fun getTrendingVideos(): Flow<PagingData<Content>> {
        loader.postValue(true)
        return repository.getTrending().onEach {
            loader.postValue(false)
        }
            .cachedIn(viewModelScope)
    }

    fun getRelated(id: String) = liveData {
        loader.postValue(true)
        emitSource(repository.getRelatedVideos(id)
            .onEach {
                loader.postValue(false)
            }.asLiveData()
        )
    }

}

