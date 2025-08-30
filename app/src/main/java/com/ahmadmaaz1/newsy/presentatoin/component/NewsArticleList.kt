package com.ahmadmaaz1.newsy.presentatoin.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.ahmadmaaz1.newsy.domain.model.Article

@Composable
fun NewsArticleList(
    modifier: Modifier = Modifier,
    article: LazyPagingItems<Article>,
    onClick : (Article) ->Unit
) {
    val handlePagingResult = handlePagingResult(article = article)
    if (handlePagingResult){
        LazyColumn (modifier = modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            contentPadding = PaddingValues(all = 11.dp))//extraSmall
        {
            items(count = article.itemCount) {
                article[it]?.let {
                    ArticleCard(article = it, onClick = {onClick(it)})
                }
            }
        }
    }

}

@Composable
fun handlePagingResult(article: LazyPagingItems<Article>) : Boolean{
    val loadState = article.loadState
    val error = when{
        loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
        loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
        loadState.append is LoadState.Error -> loadState.append as LoadState.Error
        else -> null
    }

    return when{
        loadState.refresh is LoadState.Loading -> {
            ShimmerEffect()
            false
        }
        error != null ->{
            EmptyScreen(error)
            false
        }
        else ->
            true
    }
}

@Composable
private fun ShimmerEffect() {// dimention
    Column(verticalArrangement = Arrangement.spacedBy(22.dp)) {
        repeat(10) {//dimention
            ArticleCardShimmerEffect(modifier = Modifier.padding(horizontal = 22.dp))
        }
    }
}