package com.ahmadmaaz1.newsy.presentatoin.search

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.ahmadmaaz1.newsy.domain.usecause.news.NewsUseCause
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NewsSearchViewModel @Inject constructor(private val useCause: NewsUseCause)
    : ViewModel(){
    private val _state = mutableStateOf(NewsSearchState())
     val state  = _state

    fun onEvent(event: SearchEvent){
        when(event){
            is SearchEvent.searchEvent ->{
                searchNews()
            }
            is SearchEvent.updateSearchNews -> {
                _state.value = state.value.copy(search = event.searchQuery)
            }
        }
    }

    private fun searchNews() {
        val articles = useCause.searchNews.invoke(searchQuery = state.value.search, sources =  listOf("bbc-news","abc-news","al-jazeera-english"))
            .cachedIn(viewModelScope)
        _state.value = state.value.copy(articles = articles)

    }
}