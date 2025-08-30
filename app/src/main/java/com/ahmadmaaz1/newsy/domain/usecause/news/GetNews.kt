package com.ahmadmaaz1.newsy.domain.usecause.news

import androidx.paging.PagingData
import com.ahmadmaaz1.newsy.domain.model.Article
import com.ahmadmaaz1.newsy.domain.repository.Repository
import kotlinx.coroutines.flow.Flow

class GetNews(val repository: Repository) {

    operator fun invoke (sources: List<String>): Flow<PagingData<Article>>{
        return repository.getNews(sources)
    }
}