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
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import coil.compose.AsyncImage
import com.ahmadmaaz1.newsy.domain.model.Article
import com.ahmadmaaz1.newsy.presentatoin.detail.component.DetailEvent
import com.ahmadmaaz1.newsy.presentatoin.detail.component.DetailTopAppBar
import com.ahmadmaaz1.newsy.presentatoin.detail.component.InterstitialAdManager

@Composable
fun DetailScreen(
    viewmodel: DetailViewmodel,
    article: Article,
    event: (DetailEvent) -> Unit,
    navigateUp: () -> Unit,
) {
    val context = LocalContext.current

    var showWebView by remember { mutableStateOf(false) }
    var isLoadingWeb by remember { mutableStateOf(false) }

    InterstitialAdManager.loadAd(context as Activity)


    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
    ) {

        DetailTopAppBar(
            isBookMar = viewmodel.sideEffect,
            onBackClick = navigateUp,
            onShareClick = {
                if (viewmodel.isAdsShow == 0) {
                    InterstitialAdManager.showAd(
                        activity =  context as Activity,
                        onAdsShow = { viewmodel.isAdsShow = it })
                }
                Intent(Intent.ACTION_SEND).apply {
                    putExtra(Intent.EXTRA_TEXT, article.url)
                    type = "text/plain"
                    context.startActivity(this)
                }
            },
            onBookMarkClick = {
                event(DetailEvent.SaveOrDeleteArticle(article))
            }
        )

        if (!showWebView) {

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp)
            ) {

                item {

                    AsyncImage(
                        model = article.urlToImage,
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(250.dp)
                            .clip(MaterialTheme.shapes.medium),
                        contentScale = ContentScale.Crop
                    )

                    Spacer(Modifier.height(16.dp))

                    Text(
                        text = article.title.orEmpty(),
                        style = MaterialTheme.typography.titleLarge
                    )

                    Spacer(Modifier.height(8.dp))

                    Text(
                        text = article.content.orEmpty(),
                        style = MaterialTheme.typography.bodyMedium
                    )

                    Spacer(Modifier.height(16.dp))

                    Text(
                        text = "Read Full Article →",
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.clickable {
                            if (viewmodel.isAdsShow == 0){
                                 InterstitialAdManager.showAd(activity = context as Activity, onAdsShow = {viewmodel.isAdsShow = 1})
                             }
                            showWebView = true
                        }
                    )
                }
            }

        } else {

            Box(modifier = Modifier.fillMaxSize()) {

                WebViewScreen(
                    url = article.url,
                    onLoading = { isLoadingWeb = it }
                )

                if (isLoadingWeb) {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }
        }
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

            webViewClient = object : WebViewClient(){

                override fun onPageStarted(view: WebView?, url: String?, favicon: android.graphics.Bitmap?) {
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