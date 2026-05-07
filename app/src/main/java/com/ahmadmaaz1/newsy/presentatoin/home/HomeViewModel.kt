package com.ahmadmaaz1.newsy.presentatoin.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.ahmadmaaz1.newsy.domain.model.Article
import com.ahmadmaaz1.newsy.domain.model.NewsCategory
import com.ahmadmaaz1.newsy.domain.usecause.news.GetNews
import com.ahmadmaaz1.newsy.domain.usecause.news.NewsUseCause
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val newsUseCause: NewsUseCause) : ViewModel() {

    private val _selectedCategory =
        MutableStateFlow(
            NewsCategory("All", "general")
        )

    val selectedCategory = _selectedCategory.asStateFlow()

    fun onCategoryChange(category: NewsCategory) {
        _selectedCategory.value = category
    }


    @OptIn(ExperimentalCoroutinesApi::class)
    val news = selectedCategory
        .flatMapLatest { category ->
            newsUseCause.getNews(category.apiValue)
        }
        .cachedIn(viewModelScope)


}