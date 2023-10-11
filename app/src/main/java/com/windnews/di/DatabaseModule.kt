package com.windnews.di

/*
import android.app.Application
import androidx.room.Room
import com.windnews.data.source.local.database.NewsDatabase
import com.windnews.data.source.local.database.dao.ArticleDao
import com.windnews.data.source.local.database.dao.ArticleRemoteKeyDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object NewsDatabaseModule {

    @Provides
    fun provideRepoDatabase(app: Application): NewsDatabase =
        Room.databaseBuilder(app, NewsDatabase::class.java, NewsDatabase.DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    fun provideArticleDao(db: NewsDatabase): ArticleDao = db.articleDao()

    @Provides
    fun provideArticleRemoteKeyDao(db: NewsDatabase): ArticleRemoteKeyDao = db.remoteKeyDao()
}*/
