package com.windnews.data.repository

import androidx.paging.PagingData
import com.windnews.ui.uistates.NewsListUiState
import kotlinx.coroutines.flow.Flow

interface NewsListRepository {
    fun loadNewsList(): Flow<PagingData<NewsListUiState>>
}