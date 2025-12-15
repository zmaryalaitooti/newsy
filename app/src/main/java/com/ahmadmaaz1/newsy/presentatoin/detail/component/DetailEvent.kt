package com.ahmadmaaz1.newsy.presentatoin.detail.component

import com.ahmadmaaz1.newsy.domain.model.Article

sealed class DetailEvent {

    data class SaveOrDeleteArticle(val article: Article): DetailEvent()

    object RemoveSideEffect : DetailEvent()
}