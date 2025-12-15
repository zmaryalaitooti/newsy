package com.ahmadmaaz1.newsy.presentatoin.home

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.paging.compose.LazyPagingItems
import com.ahmadmaaz1.newsy.domain.model.Article
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.ahmadmaaz1.newsy.R
import com.ahmadmaaz1.newsy.presentatoin.component.ArticleCard
import com.ahmadmaaz1.newsy.presentatoin.component.NewsArticleList
import com.ahmadmaaz1.newsy.presentatoin.component.SearchBar
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.LoadAdError


@SuppressLint("RememberReturnType")
@Composable
fun HomeScreen(
    article: LazyPagingItems<Article>,
    navigatorToDetail: (Article) -> Unit,
    navigatorToSearch: () -> Unit
) {
    val title by remember {
        derivedStateOf {
            if (article.itemCount >= 10) {
                article.itemSnapshotList.items
                    .take(10)
                    .joinToString(separator = " \uD83D\uDFE5") { it.title.orEmpty() }
            } else {
                ""
            }
        }
    }


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

        Spacer(Modifier.height(22.dp))//mediumPadding

        Spacer(modifier = Modifier.height(8.dp))
        BannerAdView()
        Spacer(modifier = Modifier.height(8.dp))

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .basicMarquee(),
            text = title.toString().uppercase(),
            style = MaterialTheme.typography.headlineSmall.copy(
                color = MaterialTheme.colorScheme.onSurface
            )
        )

        Spacer(Modifier.height(22.dp))//mediumPadding

        NewsArticleList(
            modifier = Modifier,
            article = article,
            onClick = { navigatorToDetail.invoke(it) })



    }

}


//@Composable
//fun BannerAdView() {
//    AndroidView(
//        factory = { context ->
//            AdView(context).apply {
//                 AdSize.BANNER
//                adUnitId = "ca-app-pub-3940256099942544/6300978111" // test ID
//                loadAd(AdRequest.Builder().build())
//            }
//        },
//        modifier = Modifier.fillMaxWidth()
//    )
//}



//@Composable
//fun BannerAdView() {
//
//    AndroidView(
//        modifier = Modifier.fillMaxWidth(),
//        factory = { context ->
//           var adView: AdView = AdView(context)
//            adView.setAdSize(AdSize.BANNER)
//            adView.adUnitId = "ca-app-pub-3940256099942544/6300978111"
//            adView.loadAd(AdRequest.Builder().build())
//            adView
//        })
//
//}


//@Composable
//fun BannerAdView() {
//    AndroidView(
//        modifier = Modifier
//            .fillMaxWidth()
//            .height(50.dp), // Important: give it a fixed height
//        factory = { context ->
//            AdView(context).apply {
//                 Set size and unit ID BEFORE loading
//                adUnitId = "ca-app-pub-3940256099942544/6300978111" // Test ID (good for debugging)
//                loadAd(AdRequest.Builder().build())
//            }
//        }
//    )
//}




@Composable
fun BannerAdView() {
    AndroidView(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp),
        factory = { context ->
            AdView(context).apply {
                adUnitId = "ca-app-pub-3940256099942544/6300978111"
                val adRequest = AdRequest.Builder().build()
                loadAd(adRequest)

                // Optional: Log ad events for debugging
                adListener = object : AdListener() {
                    override fun onAdLoaded() {
                        Log.d("BannerAd", "Ad loaded successfully")
                    }
                    override fun onAdFailedToLoad(error: LoadAdError) {
                        Log.e("BannerAd", "Ad failed to load: ${error.message}")
                    }
                }
            }
        },
        update = { adView ->
            // If you ever need to reload ad (e.g., on config change)
            // adView.loadAd(AdRequest.Builder().build())
        }
    )
}