package com.ahmadmaaz1.newsy.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ahmadmaaz1.newsy.domain.model.Article

@Database(entities = [Article::class], version = 1)
@TypeConverters(NewsTypeConverter::class)
abstract class NewsDb : RoomDatabase() {
    abstract fun getNewsDao() : NewsDao
}