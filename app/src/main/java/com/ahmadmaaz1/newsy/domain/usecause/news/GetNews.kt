package com.ahmadmaaz1.newsy.domain.usecause.news

import androidx.paging.PagingData
import com.ahmadmaaz1.newsy.domain.model.Article
import com.ahmadmaaz1.newsy.domain.repository.Repository
import kotlinx.coroutines.flow.Flow

class GetNews(val repository: Repository) {

    fun getNews (source: String): Flow<PagingData<Article>>{
        return repository.getNews(source)
    }

    suspend fun getBreakingNews(): List<Article>{
        return repository.getBreakingNews()
    }

    operator fun invoke (category: String): Flow<PagingData<Article>>{
        return repository.getNewsWithCategory(category)
    }
}