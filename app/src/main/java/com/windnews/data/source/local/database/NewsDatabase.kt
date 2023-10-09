package com.windnews.data.source.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.windnews.data.model.Article
import com.windnews.data.model.ArticleRemoteKey
import com.windnews.data.model.TypeConverter
import com.windnews.data.source.local.database.dao.ArticleDao

@Database(entities = [
    Article::class,
    ArticleRemoteKey::class
], version = 1, exportSchema = false)
@TypeConverters(TypeConverter::class)
abstract class NewsDatabase: RoomDatabase() {

    abstract fun articleDao(): ArticleDao

    abstract fun remoteKeyDao(): ArticleRemoteKey

    companion object {
        const val DATABASE_NAME = "news_database"
    }
}