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
        @Query("apikey") apikey: String = "d933269afaaa4718bf2b2432db1d9b2e"
    ): Response<NewsResponse>

    @GET("top-headlines")
    suspend fun getNewsWithCategory(
        @Query("page") page: Int,
        @Query("category") category: String,
        @Query("apikey") apikey: String = "d933269afaaa4718bf2b2432db1d9b2e"
    ): Response<NewsResponse>

    @GET("v2/top-headlines")
    suspend fun getBreakingNews(
        @Query("country") country: String = "us",
        @Query("page") page: Int = 1,
        @Query("apiKey") apiKey: String = "d933269afaaa4718bf2b2432db1d9b2e"
    ): Response<NewsResponse>
    @GET("everything")
    suspend fun getNewsSearch(
        @Query("page") page: Int,
        @Query("q") searchQuery: String,
        @Query("sources") sources: String,
        @Query("apikey") apikey: String = "d933269afaaa4718bf2b2432db1d9b2e"
    ): Response<NewsResponse>

}