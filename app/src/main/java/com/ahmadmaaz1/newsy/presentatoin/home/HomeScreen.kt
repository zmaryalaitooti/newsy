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

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("RememberReturnType")
@Composable
fun HomeScreen(
    article: LazyPagingItems<Article>,
    viewModel: HomeViewModel,
    navigatorToDetail: (Article) -> Unit,
    navigatorToSearch: () -> Unit
) {

    var searchText by remember { mutableStateOf("") }

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
            /// breaking news cards
            item {

                Spacer(modifier = Modifier.height(14.dp))
                BreakingNewsSlider(breakingNews = breakingNews, navigatorToDetail = navigatorToDetail)
            }

// category header
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
                }
            }
            /// category chips
            item {

                Spacer(modifier = Modifier.height(14.dp))

                // CATEGORY CHIPS

                NewsCategoryScreen(
                    onCategoryChange = { viewModel.onCategoryChange(it) },
                    selectedCategory = viewModel.selectedCategory.collectAsState().value
                )
            }

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
            // news card items
            items(article.itemCount, key = article.itemKey()) { index ->

                val news = article[index]

                news?.let {
                    NewsCard(navigatorToDetail = navigatorToDetail, article = it)
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
