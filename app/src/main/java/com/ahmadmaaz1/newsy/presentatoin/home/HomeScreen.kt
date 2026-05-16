package com.ahmadmaaz1.newsy.presentatoin.home

import android.annotation.SuppressLint
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
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
import androidx.compose.material.icons.Icons
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
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.ui.graphics.Brush
import com.ahmadmaaz1.newsy.presentatoin.bookmark.timeAgo
import kotlin.math.min

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("RememberReturnType")
@Composable
fun HomeScreen(
    article: LazyPagingItems<Article>,
    viewModel: HomeViewModel,
    navigatorToDetail: (Article) -> Unit,
    navigatorToSearch: () -> Unit
) {

    val category =
        viewModel.selectedCategory.collectAsState().value

    var searchText by remember {
        mutableStateOf("")
    }

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
                            text = "Good Morning, Ali 👋",
                            fontSize = 14.sp,
                            color = Color.Gray
                        )

                        Text(
                            text = "Here are your top stories",
                            fontSize = 13.sp,
                            color = Color.LightGray
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

                OutlinedTextField(
                    value = searchText,
                    onValueChange = {},
                    readOnly = true,
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = null
                        )
                    },
                    placeholder = {
                        Text("Search news, topics...")
                    },
                    shape = RoundedCornerShape(18.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 18.dp)
                        .clickable {
                            navigatorToSearch.invoke()
                        },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color.LightGray,
                        unfocusedBorderColor = Color.LightGray,
                        disabledBorderColor = Color.LightGray
                    )
                )
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
                ) {

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Text(
                            text = "Breaking News",
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp
                        )

                        Spacer(modifier = Modifier.width(6.dp))

                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(6.dp))
                                .background(Color.Red)
                                .padding(
                                    horizontal = 6.dp,
                                    vertical = 2.dp
                                )
                        ) {
                            Text(
                                text = "LIVE",
                                color = Color.White,
                                fontSize = 10.sp
                            )
                        }
                    }

                    Text(
                        text = "See All",
                        color = Color.Gray,
                        fontSize = 12.sp
                    )
                }
            }
            item {

                Spacer(modifier = Modifier.height(14.dp))

                HorizontalPager(
                    state = rememberPagerState(
                        pageCount = {
                            min(article.itemCount, 5)
                        }
                    ),
                    contentPadding = PaddingValues(horizontal = 18.dp),
                    pageSpacing = 12.dp,
                    modifier = Modifier.height(260.dp)
                ) { page ->

                    val news = article[page]

                    news?.let {

                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    navigatorToDetail(it)
                                },
                            shape = RoundedCornerShape(24.dp)
                        ) {

                            Box {

                                AsyncImage(
                                    model = it.urlToImage,
                                    contentDescription = null,
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier.fillMaxSize()
                                )

                                // Gradient Overlay

                                Box(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .background(
                                            Brush.verticalGradient(
                                                colors = listOf(
                                                    Color.Transparent,
                                                    Color.Black.copy(alpha = 0.85f)
                                                )
                                            )
                                        )
                                )

                                Column(
                                    modifier = Modifier
                                        .align(Alignment.BottomStart)
                                        .padding(16.dp)
                                ) {

                                    Text(
                                        text = it.source?.name ?: "News",
                                        color = Color.White,
                                        fontSize = 12.sp
                                    )

                                    Spacer(modifier = Modifier.height(6.dp))

                                    Text(
                                        text = it.title ?: "",
                                        color = Color.White,
                                        fontSize = 18.sp,
                                        fontWeight = FontWeight.Bold,
                                        maxLines = 2
                                    )

                                    Spacer(modifier = Modifier.height(10.dp))

                                    Row(
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {

                                        Icon(
                                            imageVector = Icons.Default.AccountCircle,
                                            contentDescription = null,
                                            tint = Color.White,
                                            modifier = Modifier.size(14.dp)
                                        )

                                        Spacer(modifier = Modifier.width(4.dp))

                                        Text(
                                            text = timeAgo(
                                                it.publishedAt ?: ""
                                            ),
                                            color = Color.White,
                                            fontSize = 12.sp
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }

            item {

                Spacer(modifier = Modifier.height(14.dp))

                // TOP BREAKING CARD

                article.itemSnapshotList.items.firstOrNull()?.let { news ->

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(220.dp)
                            .padding(horizontal = 18.dp)
                            .clickable {
                                navigatorToDetail(news)
                            },
                        shape = RoundedCornerShape(24.dp)
                    ) {

                        Box {

                            AsyncImage(
                                model = news.urlToImage,
                                contentDescription = null,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier.fillMaxSize()
                            )

                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(
                                        Brush.verticalGradient(
                                            colors = listOf(
                                                Color.Transparent,
                                                Color.Black.copy(alpha = 0.8f)
                                            )
                                        )
                                    )
                            )

                            Column(
                                modifier = Modifier
                                    .align(Alignment.BottomStart)
                                    .padding(16.dp)
                            ) {

                                Box(
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(6.dp))
                                        .background(Color.Red)
                                        .padding(
                                            horizontal = 8.dp,
                                            vertical = 4.dp
                                        )
                                ) {

                                    Text(
                                        text = "BREAKING",
                                        color = Color.White,
                                        fontSize = 10.sp
                                    )
                                }

                                Spacer(modifier = Modifier.height(10.dp))

                                Text(
                                    text = news.title ?: "",
                                    color = Color.White,
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Bold,
                                    maxLines = 2
                                )
                            }
                        }
                    }
                }
            }

            item {

                Spacer(modifier = Modifier.height(24.dp))

                // CATEGORY TITLE

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

                    Text(
                        text = "See All",
                        color = Color.Gray,
                        fontSize = 12.sp
                    )
                }
            }

            item {

                Spacer(modifier = Modifier.height(14.dp))

                // CATEGORY CHIPS

                NewsCategoryScreen(
                    onCategoryChange = { viewModel.onCategoryChange(it) },
                    selectedCategory = viewModel.selectedCategory.collectAsState().value
                )
            }

            item {

                Spacer(modifier = Modifier.height(24.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 18.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    Text(
                        text = "Top Stories",
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )

                    Text(
                        text = "See All",
                        color = Color.Gray,
                        fontSize = 12.sp
                    )
                }
            }

            items(article.itemCount) { index ->

                val news = article[index]

                news?.let {

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                horizontal = 18.dp,
                                vertical = 10.dp
                            )
                            .clickable {
                                navigatorToDetail(it)
                            }
                    ) {

                        AsyncImage(
                            model = it.urlToImage,
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .size(90.dp)
                                .clip(RoundedCornerShape(18.dp))
                        )

                        Spacer(modifier = Modifier.width(12.dp))

                        Column(
                            modifier = Modifier.weight(1f)
                        ) {

                            Text(
                                text = it.title ?: "",
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 15.sp,
                                maxLines = 2
                            )

                            Spacer(modifier = Modifier.height(8.dp))

                            Text(
                                text = it.source?.name ?: "",
                                color = Color.Gray,
                                fontSize = 12.sp
                            )
                        }

                        IconButton(
                            onClick = { }
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.bookmark),
                                contentDescription = null,
                                tint = Color.Gray
                            )
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
