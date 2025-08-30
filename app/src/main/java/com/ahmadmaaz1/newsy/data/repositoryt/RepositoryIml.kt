package com.ahmadmaaz1.newsy.data.repositoryt

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.ahmadmaaz1.newsy.data.remot.NewsApi
import com.ahmadmaaz1.newsy.data.remot.NewsPagingDataSource
import com.ahmadmaaz1.newsy.domain.model.Article
import com.ahmadmaaz1.newsy.domain.repository.Repository
import kotlinx.coroutines.flow.Flow

class RepositoryIml(val newsApi: NewsApi) : Repository {
    override fun getNews(sources: List<String?>?): Flow<PagingData<Article>?>? {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                NewsPagingDataSource(newsApi = newsApi, sources?.joinToString(",") ?: "")
            }
        ).flow
    }
}