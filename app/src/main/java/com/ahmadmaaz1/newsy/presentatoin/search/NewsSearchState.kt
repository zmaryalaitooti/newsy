package com.ahmadmaaz1.newsy.presentatoin.search

import androidx.paging.PagingData
import com.ahmadmaaz1.newsy.domain.model.Article
import kotlinx.coroutines.flow.Flow

data class NewsSearchState(val search: String = "",
                           val articles: Flow<PagingData<Article>>? = null)
