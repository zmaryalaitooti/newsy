package com.ahmadmaaz1.newsy.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.ahmadmaaz1.newsy.data.local.dao.NewsDao
import com.ahmadmaaz1.newsy.data.local.dao.SearchDao
import com.ahmadmaaz1.newsy.domain.model.Article
import com.ahmadmaaz1.newsy.domain.model.SearchModel

@Database(entities = [Article::class, SearchModel::class], version = 1,exportSchema = false)
@TypeConverters(NewsTypeConverter::class)
abstract class NewsDb : RoomDatabase() {
    abstract fun getNewsDao() : NewsDao
    abstract fun getSearchDao() : SearchDao
}

val MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        // Example: add a new column safely
        database.execSQL("ALTER TABLE article ADD COLUMN isBookmarked INTEGER NOT NULL DEFAULT 0")
    }
}
