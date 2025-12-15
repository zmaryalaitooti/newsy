package com.ahmadmaaz1.newsy.data.local

import com.ahmadmaaz1.newsy.domain.model.Article
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insetArticle(article: Article)

    @Delete
    suspend fun deleteArticle(article: Article)

    @Query("Select * from Article")
    fun selectArticles (): Flow<List<Article>>

    @Query("SELECT * FROM Article WHERE Url = :url")
    suspend fun selectArticleById(url : String) : Article


}