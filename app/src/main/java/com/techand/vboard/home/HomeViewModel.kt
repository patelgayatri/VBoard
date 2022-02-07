package com.techand.vboard.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.techand.vboard.data.models.Content
import com.techand.vboard.data.repository.VideosRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onStart

class HomeViewModel(private val repository: VideosRepository) : ViewModel() {

    val loader = MutableLiveData<Boolean>()

    fun fetchPosts(): Flow<PagingData<Content>> {
        loader.postValue(true)
        return repository.fetchPosts().cachedIn(viewModelScope).onStart {
            loader.postValue(false)
        }
    }

}

