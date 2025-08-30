package com.ahmadmaaz1.newsy.data.remot

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.ahmadmaaz1.newsy.domain.model.Article

class NewsPagingDataSource(val newsApi: NewsApi, val sources: String) :
    PagingSource<Int, Article>() {

    private var totalNewsCount = 0

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        val page = params.key ?: 0
        val result = newsApi.getNews(page = page, sources = sources)

        return try {
            if (result.isSuccessful && result.body() != null) {
                totalNewsCount += result.body()?.totalResults ?: 0

                val article = result.body()?.articles?.distinctBy { it.title }
                LoadResult.Page(
                    data = article!!,
                    nextKey = if (totalNewsCount == result.body()?.totalResults) null else page + 1,
                    prevKey = null
                )

            } else {
                LoadResult.Error(throwable = Throwable("the api error not data"))
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }

    }

    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return state.anchorPosition?.let { position ->
            var anchor = state.closestPageToPosition(position)
            anchor?.prevKey?.plus(1) ?: anchor?.nextKey?.minus(1)
        }
    }

}