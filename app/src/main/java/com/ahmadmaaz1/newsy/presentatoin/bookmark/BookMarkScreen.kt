package com.ahmadmaaz1.newsy.presentatoin.bookmark

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.ahmadmaaz1.newsy.R
import com.ahmadmaaz1.newsy.domain.model.Article
import com.ahmadmaaz1.newsy.presentatoin.component.NewsArticleList
import com.ahmadmaaz1.newsy.presentatoin.home.BannerAdView
import com.ahmadmaaz1.newsy.presentatoin.navgraph.Route

@Composable
fun BookMarkScreen(
    state: BookMarkState,
    navigateToDetails: (Article) -> Unit
) {
    Column(modifier = Modifier
        .fillMaxSize()
        .statusBarsPadding()
        .padding(top = 24.dp, start = 24.dp, end = 24.dp)//mediumPadding1
        )
    {
        Text(text = "BookMark",
            style = MaterialTheme.typography.displayMedium,
            color = colorResource(R.color.black) //text_title
        )

        Spacer(modifier = Modifier.height(24.dp))//mediumpadding1

        NewsArticleList(article = state.articleList, onClick = { navigateToDetails.invoke(it) })

        Spacer(modifier = Modifier.height(8.dp))
        BannerAdView()
        Spacer(modifier = Modifier.height(8.dp))
    }

}