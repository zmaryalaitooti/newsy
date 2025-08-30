package com.ahmadmaaz1.newsy.presentatoin.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.ahmadmaaz1.newsy.domain.usecause.news.GetNews
import com.ahmadmaaz1.newsy.domain.usecause.news.NewsUseCause
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val newsUseCause: NewsUseCause) : ViewModel() {

    val news = newsUseCause.getNews(sources = listOf("bbc-news","abc-news","al-jazeera-english"))
        .cachedIn(viewModelScope)

}