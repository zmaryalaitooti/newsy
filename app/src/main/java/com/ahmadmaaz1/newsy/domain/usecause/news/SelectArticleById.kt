package com.ahmadmaaz1.newsy.domain.usecause.news

import com.ahmadmaaz1.newsy.data.local.NewsDao
import com.ahmadmaaz1.newsy.domain.model.Article
import com.ahmadmaaz1.newsy.domain.repository.Repository

class SelectArticleById(private val repository: Repository) {

    suspend fun selectArticleById(url : String): Article? {
        return repository.getArticle(url = url)
    }
}