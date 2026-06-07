package com.ahmadmaaz1.newsy.presentatoin.home.componet

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.BookmarkBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.ahmadmaaz1.newsy.domain.model.Article
import com.ahmadmaaz1.newsy.presentatoin.component.getTimeAgo
import kotlinx.coroutines.delay
import kotlin.math.min

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BreakingNewsSlider(
    breakingNews: List<Article>,
    navigatorToDetail: (Article) -> Unit
) {

    val pagerState = rememberPagerState(
        pageCount = {
            min(breakingNews.size, breakingNews.size-1)
        }
    )

    // Auto Scroll

    LaunchedEffect(Unit) {

        while (true) {

            delay(8000)

            if (pagerState.pageCount > 0) {

                val nextPage =
                    (pagerState.currentPage + 1) % pagerState.pageCount

                pagerState.animateScrollToPage(nextPage)
            }
        }
    }

    Column {

        HorizontalPager(
            state = pagerState,
            contentPadding = PaddingValues(horizontal = 20.dp),
            pageSpacing = 14.dp,
            modifier = Modifier.height(240.dp)
        ) { page ->

            val news = breakingNews[page]

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        navigatorToDetail(news)
                    },

                shape = RoundedCornerShape(28.dp),

                elevation = CardDefaults.cardElevation(
                    defaultElevation = 8.dp
                )
            ) {

                Box {

                    AsyncImage(
                        model = news.urlToImage,
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )

                    // Dark Gradient

                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(
                                Brush.verticalGradient(
                                    colors = listOf(
                                        Color.Transparent,
                                        Color.Black.copy(alpha = 0.15f),
                                        Color.Black.copy(alpha = 0.92f)
                                    )
                                )
                            )
                    )

                    // Bookmark Button

//                    IconButton(
//                        onClick = {
//
//                        },
//                        modifier = Modifier
//                            .align(Alignment.TopEnd)
//                            .padding(12.dp)
//                            .background(
//                                Color.Black.copy(alpha = 0.35f),
//                                CircleShape
//                            )
//                    ) {
//
//                        Icon(
//                            imageVector = Icons.Rounded.BookmarkBorder,
//                            contentDescription = null,
//                            tint = Color.White
//                        )
//                    }

                    Column(
                        modifier = Modifier
                            .align(Alignment.BottomStart)
                            .padding(18.dp)
                    ) {

                        // Breaking Badge

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .clip(RoundedCornerShape(50))
                                .background(Color.Red)
                                .padding(
                                    horizontal = 10.dp,
                                    vertical = 4.dp
                                )
                        ) {

                            Box(
                                modifier = Modifier
                                    .size(6.dp)
                                    .background(
                                        Color.White,
                                        CircleShape
                                    )
                            )

                            Spacer(modifier = Modifier.width(6.dp))

                            Text(
                                text = "LIVE",
                                color = Color.White,
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }

                        Spacer(modifier = Modifier.height(12.dp))

                        Text(
                            text = news.title ?: "",
                            color = Color.White,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            lineHeight = 26.sp,
                            maxLines = 2
                        )

                        Spacer(modifier = Modifier.height(10.dp))

                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            Text(
                                text = news.source?.name ?: "",
                                color = Color.White.copy(alpha = 0.9f),
                                fontSize = 13.sp
                            )

                            Spacer(modifier = Modifier.width(8.dp))

                            Box(
                                modifier = Modifier
                                    .size(4.dp)
                                    .background(
                                        Color.White,
                                        CircleShape
                                    )
                            )

                            Spacer(modifier = Modifier.width(8.dp))

                            Text(
                                text = getTimeAgo(news.publishedAt ?: ""),
                                color = Color.White.copy(alpha = 0.9f),
                                fontSize = 13.sp
                            )
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Modern Indicators

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {

            repeat(pagerState.pageCount) { index ->

                val isSelected =
                    pagerState.currentPage == index

                Box(
                    modifier = Modifier
                        .padding(horizontal = 4.dp)
                        .height(8.dp)
                        .width(
                            if (isSelected) 26.dp else 8.dp
                        )
                        .clip(CircleShape)
                        .background(
                            if (isSelected)
                                Color.Red
                            else
                                Color.LightGray.copy(alpha = 0.5f)
                        )
                )
            }
        }
    }
}