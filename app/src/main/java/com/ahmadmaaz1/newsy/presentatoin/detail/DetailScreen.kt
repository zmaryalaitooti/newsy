package com.ahmadmaaz1.newsy.presentatoin.detail

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.webkit.CookieManager
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
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
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.ahmadmaaz1.newsy.R
import com.ahmadmaaz1.newsy.domain.model.Article
import com.ahmadmaaz1.newsy.domain.model.Source
import com.ahmadmaaz1.newsy.presentatoin.component.getTimeAgo
import com.ahmadmaaz1.newsy.presentatoin.detail.component.DetailEvent
import com.ahmadmaaz1.newsy.presentatoin.detail.component.DetailTopAppBar
import com.ahmadmaaz1.newsy.presentatoin.detail.component.InterstitialAdManager
import com.ahmadmaaz1.newsy.ui.theme.NewsyTheme

@Composable
fun DetailScreen(
    viewmodel: DetailViewmodel,
    article: Article,
    event: (DetailEvent) -> Unit,
    navigateUp: () -> Unit,
) {
    val context = LocalContext.current
    val activity = context as Activity

    var showWebView by remember { mutableStateOf(false) }
    var isLoadingWeb by remember { mutableStateOf(false) }

    InterstitialAdManager.loadAd(activity)

    if (showWebView) {

        Box(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
        ) {

            WebViewScreen(
                url = article.url,
                onLoading = { isLoadingWeb = it }
            )

            IconButton(
                onClick = { showWebView = false },
                modifier = Modifier
                    .padding(12.dp)
                    .align(Alignment.TopStart)
                    .background(
                        Color.Black.copy(alpha = 0.35f),
                        CircleShape
                    )
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = null,
                    tint = Color.White
                )
            }

            if (isLoadingWeb) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }

    } else {

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {

            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {

                item {

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(360.dp)
                    ) {

                        AsyncImage(
                            model = article.urlToImage,
                            contentDescription = null,
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )

                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(
                                    Brush.verticalGradient(
                                        colors = listOf(
                                            Color.Transparent,
                                            Color.Black.copy(alpha = 0.55f)
                                        )
                                    )
                                )
                        )

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .statusBarsPadding()
                                .padding(horizontal = 12.dp, vertical = 8.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            IconButton(
                                onClick = navigateUp,
                                modifier = Modifier
                                    .background(
                                        Color.Black.copy(alpha = 0.35f),
                                        CircleShape
                                    )
                            ) {
                                Icon(
                                    imageVector = Icons.Default.ArrowBack,
                                    contentDescription = null,
                                    tint = Color.White
                                )
                            }

                            Row {

                                IconButton(
                                    onClick = {
                                        event(DetailEvent.SaveOrDeleteArticle(article))
                                    },
                                    modifier = Modifier
                                        .background(
                                            Color.Black.copy(alpha = 0.35f),
                                            CircleShape
                                        )
                                ) {
                                    Icon(
                                        painter = painterResource(
                                            id = R.drawable.bookmark
                                        ),
                                        contentDescription = null,
                                        tint = Color.White,
                                        modifier = Modifier.size(22.dp)
                                    )
                                }

                                Spacer(modifier = Modifier.width(8.dp))

                                IconButton(
                                    onClick = {
                                        if (viewmodel.isAdsShow == 0) {
                                            InterstitialAdManager.showAd(
                                                activity = activity,
                                                onAdsShow = {
                                                    viewmodel.isAdsShow = it
                                                }
                                            )
                                        }

                                        Intent(Intent.ACTION_SEND).apply {
                                            putExtra(Intent.EXTRA_TEXT, article.url)
                                            type = "text/plain"
                                            context.startActivity(this)
                                        }
                                    },
                                    modifier = Modifier
                                        .background(
                                            Color.Black.copy(alpha = 0.35f),
                                            CircleShape
                                        )
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Share,
                                        contentDescription = null,
                                        tint = Color.White
                                    )
                                }
                            }
                        }
                    }

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .offset(y = (-28).dp),
                        shape = RoundedCornerShape(
                            topStart = 28.dp,
                            topEnd = 28.dp
                        ),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.surface
                        )
                    ) {

                        Column(
                            modifier = Modifier.padding(20.dp)
                        ) {

                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Box(
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(50))
                                        .background(Color.Red)
                                        .padding(
                                            horizontal = 10.dp,
                                            vertical = 4.dp
                                        )
                                ) {
                                    Text(
                                        modifier = Modifier.clickable(enabled = true, onClick = {
                                            if (viewmodel.isAdsShow == 0) {
                                                InterstitialAdManager.showAd(
                                                    activity = activity,
                                                    onAdsShow = {
                                                        viewmodel.isAdsShow = 1
                                                    }
                                                )
                                            }
                                            showWebView = true
                                        }),
                                        text = "LIVE",
                                        color = Color.White,
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                }
                            }

                            Spacer(modifier = Modifier.height(14.dp))

                            Text(
                                text = article.title.orEmpty(),
                                fontSize = 28.sp,
                                fontWeight = FontWeight.Bold,
                                lineHeight = 34.sp
                            )

                            Spacer(modifier = Modifier.height(12.dp))

                            Text(
                                text = "${article.source.name ?: "News"} • ${getTimeAgo(article.publishedAt!!)}",
                                color = Color.Gray,
                                fontSize = 14.sp
                            )

                            Spacer(modifier = Modifier.height(18.dp))

                            Text(
                                text = article.content.orEmpty(),
                                fontSize = 17.sp,
                                lineHeight = 26.sp,
                                color = MaterialTheme.colorScheme.onSurface
                            )

                            Spacer(modifier = Modifier.height(20.dp))

                            Button(
                                onClick = {
                                    if (viewmodel.isAdsShow == 0) {
                                        InterstitialAdManager.showAd(
                                            activity = activity,
                                            onAdsShow = {
                                                viewmodel.isAdsShow = 1
                                            }
                                        )
                                    }

                                    showWebView = true
                                },
                                modifier = Modifier.fillMaxWidth(),
                                shape = RoundedCornerShape(14.dp)
                            ) {
                                Text("Read Full Article")
                            }

                            Spacer(modifier = Modifier.height(20.dp))
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun NewsChip(text: String) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(50))
            .background(Color(0xFFF1F1F1))
            .padding(horizontal = 14.dp, vertical = 8.dp)
    ) {
        Text(
            text = text,
            fontSize = 13.sp,
            color = Color.DarkGray
        )
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun DetailScreenPreview() {

    val dummyArticle = Article(
        author = "BBC News",
        title = "SpaceX launches new satellite to improve global internet coverage",
        description = "SpaceX launched a new batch of satellites.",
        content = "SpaceX launched a new batch of satellites on Monday as part of its mission to provide high-speed internet access worldwide.\n\nThe Falcon 9 rocket lifted off from Cape Canaveral, Florida carrying 60 Starlink satellites into low Earth orbit.\n\nThis mission marks another major step in expanding global connectivity.",
        publishedAt = "2026-05-11",
        source = Source(
            id = "bbc-news",
            name = "BBC News"
        ),
        url = "https://www.bbc.com",
        urlToImage = "https://images.unsplash.com/photo-1446776811953-b23d57bd21aa"
    )

    NewsyTheme {

        DetailScreen(
            viewmodel = hiltViewModel<DetailViewmodel>(),   // use direct instance
            article = dummyArticle,
            event = {},
            navigateUp = {}
        )
    }
}


@SuppressLint("SetJavaScriptEnabled")
@Composable
fun WebViewScreen(
    url: String,
    onLoading: (Boolean) -> Unit
) {
    AndroidView(factory = { context ->
        WebView(context).apply {
            // Disable cookies (both first and third party)
            CookieManager.getInstance().setAcceptCookie(false)
            CookieManager.getInstance().setAcceptThirdPartyCookies(this, false)

            webViewClient = object : WebViewClient() {

                override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                    onLoading(true)
                }

                override fun onPageFinished(view: WebView?, url: String?) {
                    onLoading(false)
                }
            }

            settings.apply {
                javaScriptEnabled = false               // Disable JavaScript
                domStorageEnabled =
                    false               // Disable DOM storage (localStorage/sessionStorage)
                cacheMode = WebSettings.LOAD_NO_CACHE   // Disable caching
//                setAppCacheEnabled(false)
                loadsImagesAutomatically = true        // Disable image loading
                databaseEnabled = false
                savePassword = false
                saveFormData = false
                setSupportZoom(false)
                builtInZoomControls = false
                displayZoomControls = false
                allowFileAccess = false
                allowContentAccess = false
                setGeolocationEnabled(false)
                mixedContentMode = WebSettings.MIXED_CONTENT_NEVER_ALLOW
                userAgentString = "SecureWebView"       // Override user agent
            }

            clearCache(true)
            clearHistory()
            clearFormData()

            loadUrl(url)
        }
    })
}