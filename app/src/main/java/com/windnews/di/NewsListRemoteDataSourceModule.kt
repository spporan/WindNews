package com.windnews.di

import com.windnews.data.source.remote.NewsListRemoteDataSource
import com.windnews.data.source.remote.api.NewsApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object NewsListRemoteDataSourceModule {
    @Provides
    fun provideNewsListDataSourceModule(newsApiService: NewsApiService): NewsListRemoteDataSource  {
        return NewsListRemoteDataSource(newsApiService)
    }
}