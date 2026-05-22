package com.ahmadmaaz1.newsy.presentatoin.search

sealed class SearchEvent {

    data class UpdateSearchNews(val searchQuery: String) : SearchEvent()

    object searchEvent : SearchEvent()
}