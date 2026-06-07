package com.ahmadmaaz1.newsy.data.repositoryt

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.ahmadmaaz1.newsy.data.local.dao.NewsDao
import com.ahmadmaaz1.newsy.data.local.dao.SearchDao
import com.ahmadmaaz1.newsy.data.remot.NewsApi
import com.ahmadmaaz1.newsy.data.remot.NewsPagingDataSource
import com.ahmadmaaz1.newsy.data.remot.NewsSearchPagingDataSource
import com.ahmadmaaz1.newsy.domain.model.Article
import com.ahmadmaaz1.newsy.domain.model.SearchModel
import com.ahmadmaaz1.newsy.domain.repository.Repository
import com.ahmadmaaz1.newsy.presentatoin.search.SearchEvent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class RepositoryIml(val newsApi: NewsApi, val newsDao: NewsDao, val searchDao: SearchDao) :
    Repository {
    override fun getNews(source: String): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                NewsPagingDataSource(newsApi = newsApi, source)
            }
        ).flow
    }

    override suspend fun getBreakingNews(): List<Article> {
        val result = newsApi.getBreakingNews()
        return if (result.isSuccessful && result.body() != null) {
            Log.d("HomeViewModel", "getBreakingNews: ${result.code()} ")

            result.body()!!.articles
        } else {
            Log.d("HomeViewModel", "getBreakingNews in else : ${result.code()} ")
            emptyList()
        }


    }

    override fun getNewsWithCategory(category: String): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                NewsPagingDataSource(newsApi = newsApi, category)
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

    override fun getNewsSuggestion(): Flow<PagingData<SearchModel>> {

        return searchDao.selectSuggestions().map { list ->
            PagingData.from(list)
        }


    }

                override suspend fun insertSuggestion(suggestion: String) {
            searchDao.insetSuggestion(SearchModel(search = suggestion))
        }


                override suspend fun insertArticle(article: Article) {
            newsDao.insetArticle(article)
        }

                override suspend fun deleteArticle(article: Article) {
            newsDao.deleteArticle(article)
        }

                override fun getArticles(): Flow<List<Article>> {
            return newsDao.selectArticles()
        }

                override suspend fun getArticle(url: String): Article {
            return newsDao.selectArticleById(url)
        }


    }