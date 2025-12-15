package com.ahmadmaaz1.newsy.presentatoin.detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahmadmaaz1.newsy.domain.model.Article
import com.ahmadmaaz1.newsy.domain.usecause.news.NewsUseCause
import com.ahmadmaaz1.newsy.presentatoin.detail.component.DetailEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewmodel @Inject constructor(val newsUseCause: NewsUseCause) : ViewModel() {

    var sideEffect by mutableStateOf<String>("")
        private set



    fun onEven(event: DetailEvent){
        when(event){
            is DetailEvent.SaveOrDeleteArticle -> {
                viewModelScope.launch {
                    val article : Article? = newsUseCause.selectArticleById.selectArticleById(event.article.url)
                    if (article == null){
                        insertArticle(event.article)
                    }
                    else{
                        deleteArticle(article)
                    }
                }

            }

           is  DetailEvent.RemoveSideEffect -> {
                sideEffect = ""
            }
        }
    }

    private suspend fun insertArticle(article: Article) {

        newsUseCause.insertArticle.insertArticle(article)
        sideEffect = "Article Saved"

    }

    private suspend fun deleteArticle(article: Article) {

        newsUseCause.deleteArticle.deleteArticle(article)
        sideEffect = "Article Deleted"
    }

}


