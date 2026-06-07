package com.ahmadmaaz1.newsy.presentatoin.home

import android.annotation.SuppressLint
import androidx.paging.LoadState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.paging.compose.LazyPagingItems
import com.ahmadmaaz1.newsy.domain.model.Article
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import coil.compose.AsyncImage
import com.ahmadmaaz1.newsy.R
import com.ahmadmaaz1.newsy.presentatoin.component.NewsArticleList
import com.ahmadmaaz1.newsy.presentatoin.component.SearchBar
import com.ahmadmaaz1.newsy.presentatoin.home.componet.HomeDrawerMenu
import com.ahmadmaaz1.newsy.presentatoin.newscategory.NewsCategoryScreen
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.graphics.Brush
import androidx.paging.compose.itemKey
import com.ahmadmaaz1.newsy.presentatoin.bookmark.timeAgo
import com.ahmadmaaz1.newsy.presentatoin.component.getTimeAgo
import com.ahmadmaaz1.newsy.presentatoin.home.componet.BreakingNewsSlider
import com.ahmadmaaz1.newsy.presentatoin.home.componet.NewsCard
import kotlin.math.min
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.LinearProgressIndicator

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("RememberReturnType")
@Composable
fun HomeScreen(
    article: LazyPagingItems<Article>,
    viewModel: HomeViewModel,
    navigatorToDetail: (Article) -> Unit,
    navigatorToSeeAll: (BreakingNewsState) -> Unit,
    navigatorToSearch: () -> Unit
) {

    val isFirstLoading =
        article.loadState.refresh is LoadState.Loading &&
                article.itemCount == 0

    val breakingNews by viewModel.breakingNews.collectAsState()

    HomeDrawerMenu {

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .statusBarsPadding(),
            contentPadding = PaddingValues(bottom = 20.dp)
        )
        {
            // morning item
            item {
                // TOP BAR
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 18.dp, vertical = 10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Column {

                        Spacer(modifier = Modifier.height(4.dp))

                        Text(
                            text = "Good Morning 👋",
                            fontSize = 14.sp,
                            color = Color.Gray
                        )

                        Text(
                            text = "Here are your top stories",
                            fontSize = 13.sp,
                            color = Color.Gray
                        )
                    }

                    IconButton(
                        onClick = { }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Notifications,
                            contentDescription = null,
                            tint = Color.Black
                        )
                    }
                }
            }
            // Search
            item {

                // SEARCH BAR

                Spacer(modifier = Modifier.height(8.dp))

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 18.dp)
                        .clickable { navigatorToSearch() }
                ) {
                    OutlinedTextField(
                        value = "",
                        onValueChange = {},
                        enabled = false,
                        placeholder = { Text("Search news, topics...") },
                        leadingIcon = {
                            Icon(Icons.Default.Search, contentDescription = null)
                        },
                        shape = RoundedCornerShape(18.dp),
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
            // breaking news headers
            item {

                Spacer(modifier = Modifier.height(20.dp))

                // BREAKING NEWS TITLE
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 18.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                )
                {

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    )
                    {

                        Text(
                            text = "Breaking News",
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp
                        )

                        Spacer(modifier = Modifier.width(6.dp))

//                        Box(
//                            modifier = Modifier
//                                .clip(RoundedCornerShape(6.dp))
//                                .background(Color.Red)
//                                .padding(
//                                    horizontal = 6.dp,
//                                    vertical = 2.dp
//                                )
//                        ) {
//                            Text(
//                                text = "LIVE",
//                                color = Color.White,
//                                fontSize = 10.sp
//                            )
//                        }
                    }

                    Text(
                        modifier = Modifier.clickable {
                            navigatorToSeeAll(breakingNews)
                        },
                        text = "See All",
                        color = Color.Gray,
                        fontSize = 12.sp
                    )
                }
            }


            // ---------------------------
            // ONE LOADING STATE
            // ---------------------------

            if (
                breakingNews is BreakingNewsState.Loading ||
                isFirstLoading
            ) {

                item {

                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(top = 120.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }
            }

            // ---------------------------
            // ONE ERROR STATE
            // ---------------------------

            else if (
                breakingNews is BreakingNewsState.Error ||
                article.loadState.refresh is LoadState.Error
            ) {

                val errorMessage = when {

                    breakingNews is BreakingNewsState.Error -> {
                        (breakingNews as BreakingNewsState.Error).message
                    }

                    article.loadState.refresh is LoadState.Error -> {
                        (article.loadState.refresh as LoadState.Error)
                            .error
                            .localizedMessage ?: "Something went wrong"
                    }

                    else -> "Something went wrong"
                }

                item {

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp)
                            .clip(RoundedCornerShape(16.dp))
                            .background(Color(0xFFFFF3F3))
                            .padding(20.dp),
                        contentAlignment = Alignment.Center
                    ) {

                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {

                            Icon(
                                imageVector = Icons.Default.Warning,
                                contentDescription = null,
                                tint = Color.Red,
                                modifier = Modifier.size(34.dp)
                            )

                            Spacer(modifier = Modifier.height(10.dp))

                            Text(
                                text = errorMessage,
                                color = Color.Red,
                                fontWeight = FontWeight.Medium,
                                textAlign = TextAlign.Center
                            )

                            Spacer(modifier = Modifier.height(12.dp))

                            Button(
                                onClick = {

                                    // retry breaking news
                                    if (breakingNews is BreakingNewsState.Error) {
                                        viewModel.getNewsAll()
                                    }

                                    // retry paging
                                    article.retry()
                                },
                                shape = RoundedCornerShape(12.dp)
                            ) {
                                Text("Retry")
                            }
                        }
                    }
                }
            }

            // ---------------------------
            // SUCCESS UI
            // ---------------------------

            else {

                // breaking news content
                item {

                    Spacer(modifier = Modifier.height(14.dp))

                    if (breakingNews is BreakingNewsState.Success) {

                        BreakingNewsSlider(
                            breakingNews = (breakingNews as BreakingNewsState.Success).news,
                            navigatorToDetail = navigatorToDetail
                        )
                    }
                }

                // category header
                item {

                    Spacer(modifier = Modifier.height(24.dp))

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 18.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {

                        Text(
                            text = "Categories",
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp
                        )
                    }
                }

                // category chips
                item {

                    Spacer(modifier = Modifier.height(14.dp))

                    NewsCategoryScreen(
                        onCategoryChange = {
                            viewModel.onCategoryChange(it)
                        },
                        selectedCategory = viewModel
                            .selectedCategory
                            .collectAsState()
                            .value
                    )
                }

                // top stories title
                item {

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 18.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {

                        Text(
                            text = "Top Stories",
                            style = MaterialTheme.typography.headlineMedium,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(horizontal = 16.dp)
                        )
                    }
                }

                if (
                    article.loadState.refresh is LoadState.Loading &&
                    article.itemCount > 0
                ) {

                    item {

                        LinearProgressIndicator(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 18.dp)
                        )

                        Spacer(modifier = Modifier.height(10.dp))
                    }
                }
                // news list
                items(
                    count = article.itemCount,
                    key = article.itemKey()
                ) { index ->


                    val news = article[index]

                    news?.let {

                        NewsCard(
                            navigatorToDetail = navigatorToDetail,
                            article = it
                        )
                    }
                }

                // pagination loading
                if (article.loadState.append is LoadState.Loading) {

                    item {

                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator()
                        }
                    }
                }
            }
        }
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
