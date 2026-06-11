package com.ahmadmaaz1.newsy.presentatoin.home

import com.ahmadmaaz1.newsy.domain.model.Article

sealed class BreakingNewsState {

   public object Loading : BreakingNewsState()

  public  data class Success(val news: List<Article>) : BreakingNewsState()

    data class Error(val message: String) : BreakingNewsState()
}
