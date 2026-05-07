package com.ahmadmaaz1.newsy.presentatoin.home

import android.annotation.SuppressLint
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.paging.compose.LazyPagingItems
import com.ahmadmaaz1.newsy.domain.model.Article
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.ahmadmaaz1.newsy.R
import com.ahmadmaaz1.newsy.presentatoin.component.NewsArticleList
import com.ahmadmaaz1.newsy.presentatoin.component.SearchBar
import com.ahmadmaaz1.newsy.presentatoin.home.componet.TopBarMenu
import com.ahmadmaaz1.newsy.presentatoin.newscategory.NewsCategoryScreen
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("RememberReturnType")
@Composable
fun HomeScreen(
    article: LazyPagingItems<Article>,
    viewModel: HomeViewModel,
    navigatorToDetail: (Article) -> Unit,
    navigatorToSearch: () -> Unit
) {

    TopBarMenu()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 22.dp)  //mediumPadding
            .statusBarsPadding()
    )
    {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = null,
            modifier = Modifier
                .width(150.dp)
                .height(30.dp)
                .padding(horizontal = 22.dp) // mediumPadding1
        )

        Spacer(Modifier.height(22.dp))//mediumPadding

        var searchText by remember { mutableStateOf("") }
        SearchBar(
            modifier = Modifier.fillMaxWidth(),
            text = searchText,
            readOnly = true,
            onclick = {
                navigatorToSearch.invoke()
                Log.d("TAG", "HomeScreen: clicked ")
            },
            onSearch = {},
            onValueChange = { searchText = it }
        )

        Spacer(Modifier.height(11.dp))//mediumPadding

        BannerAdView()

        Spacer(modifier = Modifier.height(8.dp))


        NewsCategoryScreen(
            { viewModel.onCategoryChange(it) },
            selectedCategory = viewModel.selectedCategory.collectAsState().value
        )

        NewsArticleList(
            modifier = Modifier,
            article = article,
            onClick = { navigatorToDetail.invoke(it) })

    }
}
@Composable
fun BannerAdView() {
    AndroidView(
        modifier = Modifier.fillMaxWidth(),
        factory = { context ->
            val adView = AdView(context)
            adView.adUnitId = "ca-app-pub-8992718827220232/3361878081"
            val adSize = AdSize.getCurrentOrientationAnchoredAdaptiveBannerAdSize(
                context,
                (context.resources.displayMetrics.widthPixels / context.resources.displayMetrics.density).toInt()
            )
            adView.setAdSize(adSize)
            adView.loadAd(AdRequest.Builder().build())
            adView
        }
    )
}
