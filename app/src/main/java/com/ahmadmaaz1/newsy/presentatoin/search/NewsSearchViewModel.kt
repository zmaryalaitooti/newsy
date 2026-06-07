package com.ahmadmaaz1.newsy.presentatoin.search

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.insertFooterItem
import androidx.paging.map
import com.ahmadmaaz1.newsy.domain.model.SearchModel
import com.ahmadmaaz1.newsy.domain.usecause.news.NewsUseCause
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsSearchViewModel @Inject constructor(private val useCause: NewsUseCause) : ViewModel() {
    private val _state = mutableStateOf(NewsSearchState())
    val state = _state

    var suggestions: Flow<PagingData<SearchModel>> =
        useCause.searchNews.getSuggestions()
            .cachedIn(viewModelScope)



//    @SuppressLint("CheckResult")
//    private fun getSuggestions() {
//
//        viewModelScope.launch {
//
//            useCause.searchNews.getSuggestions()
//                .cachedIn(viewModelScope)
//                .collect { pagingData ->
//
//                    _suggestions.value = pagingData
//                }
//        }
//    }

    fun insertSuggestion(search: String) {
        if (search.isBlank()) return

        viewModelScope.launch {

            Log.d("search", "insertSuggestion: $search")
            useCause.searchNews.insertSuggestion(
                search = search.trim()
            )
        }
    }

    fun onEvent(event: SearchEvent) {
        when (event) {
            is SearchEvent.searchEvent -> {
                searchNews()
            }

            is SearchEvent.UpdateSearchNews -> {
                _state.value = state.value.copy(search = event.searchQuery)
            }
        }
    }


    private fun searchNews() {
        val articles = useCause.searchNews.invoke(
            searchQuery = state.value.search,
            sources = listOf("bbc-news", "abc-news", "al-jazeera-english")
        )
            .cachedIn(viewModelScope)
        _state.value = state.value.copy(articles = articles)

    }
}