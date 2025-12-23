package com.ahmadmaaz1.newsy.presentatoin.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.paging.compose.collectAsLazyPagingItems
import com.ahmadmaaz1.newsy.R
import com.ahmadmaaz1.newsy.domain.model.Article
import com.ahmadmaaz1.newsy.presentatoin.component.NewsArticleList
import com.ahmadmaaz1.newsy.presentatoin.component.SearchBar

@Composable
fun SearchScreen(
    state: NewsSearchState,
    event: (SearchEvent) -> Unit,
    navigateToDetails: (Article) -> Unit
) {

    Column(
        modifier = Modifier
            .padding(top = 24.dp)
            .statusBarsPadding()
            .fillMaxSize()
    ) {
        Text(text = "Search ",
            style = MaterialTheme.typography.displayMedium,
            color = colorResource(R.color.black) //text_title
        )

        SearchBar( isKeyboardShow = true
            , modifier = Modifier.fillMaxWidth().padding(top = 22.dp),
            text = state.search,
            onclick = {},
            readOnly = false,
            onSearch = {
            event.invoke(
                SearchEvent.searchEvent
            )
        },
            onValueChange = {
            event(
                SearchEvent.updateSearchNews(it)
            )
        })

        Spacer(Modifier.height(24.dp))

        state.articles?.let {
            val articles = it.collectAsLazyPagingItems()
            NewsArticleList(article = articles, onClick = { navigateToDetails(it) })

        }

    }

}