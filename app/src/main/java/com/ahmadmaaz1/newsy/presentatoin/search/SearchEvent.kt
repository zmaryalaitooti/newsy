package com.ahmadmaaz1.newsy.presentatoin.search

sealed class SearchEvent {

    data class updateSearchNews(val searchQuery:String ): SearchEvent()

    object searchEvent: SearchEvent()
}