package com.ahmadmaaz1.newsy.data.remot.dto

import com.ahmadmaaz1.newsy.domain.model.Article

data class NewsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)