package com.ahmadmaaz1.newsy.domain.repository

import androidx.paging.PagingData
import com.ahmadmaaz1.newsy.domain.model.Article
import kotlinx.coroutines.flow.Flow

interface Repository {

    fun getNews(sources: List<String?>?): Flow<PagingData<Article>>

    fun getNewsSearch(
        searchQuery: String?,
        sources: List<String?>?
    ): Flow<PagingData<Article>>

   suspend fun insertArticle(article: Article)

    suspend fun deleteArticle(article: Article)

    fun getArticles(): Flow<List<Article>>

    suspend fun getArticle(url: String): Article
}