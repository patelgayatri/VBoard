package com.techand.vboard.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.techand.vboard.data.repository.VideosRepository
import javax.inject.Inject

class HomeViewModelFactory @Inject constructor(private val repository: VideosRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HomeViewModel(repository) as T
    }
}