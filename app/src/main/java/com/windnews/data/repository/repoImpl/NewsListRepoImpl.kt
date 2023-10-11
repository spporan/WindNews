package com.windnews.data.repository.repoImpl

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.windnews.data.repository.NewsListRepository
import com.windnews.data.source.paging.NewsListPagingSource
import com.windnews.ui.uistates.NewsListUiState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NewsListRepoImpl @Inject constructor(
    private val newsListPagingSource: NewsListPagingSource,
): NewsListRepository {
    companion object {
        const val NETWORK_PAGE_SIZE = 30
    }

    override fun loadNewsList(): Flow<PagingData<NewsListUiState>> {
        return Pager(
            // Configure how data is loaded by passing additional properties to
            // PagingConfig, such as prefetchDistance.
            PagingConfig(pageSize = NETWORK_PAGE_SIZE)
        ) {
            newsListPagingSource
        }.flow
    }
}