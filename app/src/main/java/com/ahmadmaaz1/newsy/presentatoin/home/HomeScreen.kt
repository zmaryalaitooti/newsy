package com.ahmadmaaz1.newsy.presentatoin.home

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.paging.compose.LazyPagingItems
import com.ahmadmaaz1.newsy.domain.model.Article
import androidx.compose.runtime.getValue

@SuppressLint("RememberReturnType")
@Composable
fun HomeScreen(
    article: LazyPagingItems<Article>,
    navigator: (String) -> Unit
) {
    val title = remember {
        derivedStateOf {
            if (article.itemCount > 10) {
                article.itemSnapshotList.items.slice(IntRange(start = 0, endInclusive = 9))
                    .joinToString(separator = " \uD83d\uDFE5") {
                        it.title.toString()
                    }
            } else {
                ""
            }
        }


    }
}