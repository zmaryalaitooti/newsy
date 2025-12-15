package com.ahmadmaaz1.newsy.data.repositoryt

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.ahmadmaaz1.newsy.data.local.NewsDao
import com.ahmadmaaz1.newsy.data.remot.NewsApi
import com.ahmadmaaz1.newsy.data.remot.NewsPagingDataSource
import com.ahmadmaaz1.newsy.data.remot.NewsSearchPagingDataSource
import com.ahmadmaaz1.newsy.domain.model.Article
import com.ahmadmaaz1.newsy.domain.repository.Repository
import kotlinx.coroutines.flow.Flow

class RepositoryIml(val newsApi: NewsApi, val newsDao: NewsDao) : Repository {
    override fun getNews(sources: List<String?>?): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                NewsPagingDataSource(newsApi = newsApi, sources?.joinToString(",") ?: "")
            }
        ).flow
    }

    override fun getNewsSearch(
        searchQuery: String?,
        sources: List<String?>?
    ): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                NewsSearchPagingDataSource(
                    searchQuery = searchQuery.toString(), newsApi = newsApi,
                    sources?.joinToString(",") ?: ""
                )
            }
        ).flow
    }


    override suspend fun insertArticle(article: Article) {
        newsDao.insetArticle(article)
    }

    override suspend fun deleteArticle(article: Article) {
        newsDao.deleteArticle(article)
    }

    override  fun getArticles(): Flow<List<Article>> {
        return newsDao.selectArticles()
    }

    override suspend fun getArticle(url: String): Article {
        return newsDao.selectArticleById(url)
    }


}