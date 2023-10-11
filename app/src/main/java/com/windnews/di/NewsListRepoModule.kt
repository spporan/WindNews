package com.windnews.di

import com.windnews.data.repository.NewsListRepository
import com.windnews.data.repository.repoImpl.NewsListRepoImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class NewsListRepoModule {

    @Binds
    abstract fun bindNewsListRepo(
        newsListRepoImpl: NewsListRepoImpl
    ): NewsListRepository

}