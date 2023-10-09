package com.windnews.data.source.remote

import com.windnews.data.source.remote.api.NewsApiService
import javax.inject.Inject

class NewsListRemoteDataSource @Inject constructor(
    private val newsApi: NewsApiService
)  {
    companion object{
        const val ITEM_PER_PAGE  = 30
    }

    suspend fun fetchNewsList(
        apiKey: String,
        page: Int,
        pageSize: Int = ITEM_PER_PAGE
    ) = newsApi.getListOfNews(apiKey, page, pageSize)
}