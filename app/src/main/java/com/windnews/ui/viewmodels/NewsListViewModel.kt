package com.windnews.ui.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.windnews.data.repository.NewsListRepository
import com.windnews.ui.uistates.NewsListUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class NewsListViewModel @Inject constructor(
    private val repository: NewsListRepository
    ): ViewModel() {
        val newsFlow: Flow<PagingData<NewsListUiState>>

        init {
            Log.e("init", "view model.....")
            newsFlow = loadNews()
        }

     private fun loadNews(): Flow<PagingData<NewsListUiState>> {
        return repository
            .loadNewsList()
            .cachedIn(viewModelScope)
    }


}