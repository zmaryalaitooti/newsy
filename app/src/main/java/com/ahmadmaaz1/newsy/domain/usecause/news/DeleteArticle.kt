package com.ahmadmaaz1.newsy.domain.usecause.news

import com.ahmadmaaz1.newsy.domain.model.Article
import com.ahmadmaaz1.newsy.domain.repository.Repository

class DeleteArticle (private val repository: Repository){

    suspend fun deleteArticle(article: Article){
        repository.deleteArticle(article = article)
    }
}