package com.ahmadmaaz1.newsy.domain.usecause.news

data class NewsUseCause(
    val getNews: GetNews,
    val searchNews: NewsSearch,
    val selectArticles: SelectArticles,
    val deleteArticle: DeleteArticle,
    val insertArticle: InsertArticle,
    val selectArticleById: SelectArticleById
)
