package com.windnews.di

import com.windnews.data.source.paging.NewsListPagingSource
import com.windnews.data.source.remote.NewsListRemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
object NewsListPagingSourceModule {
    @Provides
    fun provideNewsListPagingSource( @Named("api_key") apiKey: String, newsListRemoteDataSource: NewsListRemoteDataSource): NewsListPagingSource {
        return NewsListPagingSource(newsListRemoteDataSource, apiKey)
    }
}