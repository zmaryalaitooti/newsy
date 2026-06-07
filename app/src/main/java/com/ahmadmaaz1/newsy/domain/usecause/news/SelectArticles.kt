package com.ahmadmaaz1.newsy.domain.usecause.news

import com.ahmadmaaz1.newsy.data.local.dao.NewsDao
import com.ahmadmaaz1.newsy.domain.model.Article
import com.ahmadmaaz1.newsy.domain.repository.Repository
import kotlinx.coroutines.flow.Flow

class SelectArticles(private val repository: Repository) {

    fun selectArticles(): Flow<List<Article>> {
        return repository.getArticles()
    }
}