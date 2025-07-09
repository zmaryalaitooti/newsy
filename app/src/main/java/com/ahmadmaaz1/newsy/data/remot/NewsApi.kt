package com.ahmadmaaz1.newsy.data.remot

import com.ahmadmaaz1.newsy.data.remot.dto.NewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("everything")
    suspend fun getNews(
        @Query("page") page: Int,
        @Query("sources") sources: String,
        @Query("apikey") apikey : String = "d933269afaaa4718bf2b2432db1d9b2e"
    ): Response<NewsResponse>

}