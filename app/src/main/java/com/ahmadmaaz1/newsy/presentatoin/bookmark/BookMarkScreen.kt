package com.ahmadmaaz1.newsy.presentatoin.bookmark

import androidx.compose.ui.graphics.Color
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
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.ahmadmaaz1.newsy.R
import com.ahmadmaaz1.newsy.domain.model.Article
import com.ahmadmaaz1.newsy.domain.model.Source
import com.ahmadmaaz1.newsy.ui.theme.NewsyTheme
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.isSystemInDarkTheme
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone
import java.util.concurrent.TimeUnit

@Composable
fun BookMarkScreen(
    state: BookMarkState,
    navigateToDetails: (Article) -> Unit
) {

    val isDark = isSystemInDarkTheme()

    val backgroundColor = if (isDark) Color(0xFF121212) else Color.White
    val textColor = if (isDark) Color.White else Color.Black
    val secondaryText = if (isDark) Color(0xFFB0B0B0) else Color.Gray

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .statusBarsPadding()
    ) {

        // Top Title
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 14.dp, bottom = 10.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Bookmarks",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = textColor
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Saved Articles",
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
            color = textColor,
            modifier = Modifier.padding(horizontal = 18.dp)
        )

        Spacer(modifier = Modifier.height(14.dp))

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(18.dp)
        ) {

            items(state.articleList) { article ->

                BookmarkItem(
                    article = article,
                    textColor = textColor,
                    secondaryText = secondaryText,
                    onClick = {
                        navigateToDetails(article)
                    }
                )
            }

            item {
                Spacer(modifier = Modifier.height(80.dp))
            }
        }
    }
}

@Composable
fun BookmarkItem(
    article: Article,
    textColor: Color,
    secondaryText: Color,
    onClick: () -> Unit
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        verticalAlignment = Alignment.Top
    ) {

        AsyncImage(
            model = article.urlToImage,
            contentDescription = null,
            modifier = Modifier
                .size(width = 92.dp, height = 82.dp)
                .clip(RoundedCornerShape(16.dp)),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.width(14.dp))

        Column(
            modifier = Modifier.weight(1f)
        ) {

            Text(
                text = article.title.orEmpty(),
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                color = textColor,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis,
                lineHeight = 24.sp
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "${article.source.name ?: "BBC News"} • ${timeAgo(article.publishedAt.orEmpty())}",
                fontSize = 14.sp,
                color = secondaryText
            )
        }

        Spacer(modifier = Modifier.width(10.dp))

        Icon(
            painter = painterResource(id = R.drawable.bookmark),
            contentDescription = null,
            tint = Color(0xFFE53935),
            modifier = Modifier.size(20.dp)
        )
    }
}

fun timeAgo(dateTime: String): String {
    return try {

        val format = SimpleDateFormat(
            "yyyy-MM-dd'T'HH:mm:ss'Z'",
            Locale.getDefault()
        )

        format.timeZone = TimeZone.getTimeZone("UTC")

        val publishedDate: Date = format.parse(dateTime) ?: return "Recently"
        val now = Date()

        val diff = now.time - publishedDate.time

        val minutes = TimeUnit.MILLISECONDS.toMinutes(diff)
        val hours = TimeUnit.MILLISECONDS.toHours(diff)
        val days = TimeUnit.MILLISECONDS.toDays(diff)

        when {
            minutes < 1 -> "Just now"
            minutes < 60 -> "$minutes min ago"
            hours < 24 -> "$hours hr ago"
            days < 7 -> "$days day ago"
            days < 30 -> "${days / 7} week ago"
            days < 365 -> "${days / 30} month ago"
            else -> "${days / 365} year ago"
        }

    } catch (e: Exception) {
        "Recently"
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun BookMarkScreenPreview() {

    val sampleArticles = listOf(

        Article(
            author = "BBC News",
            title = "Global warming will worsen in the next decade, report says",
            description = "",
            content = "",
            publishedAt = "2026-05-11",
            source = Source(
                id = "bbc-news",
                name = "BBC News"
            ),
            url = "https://bbc.com",
            urlToImage = "https://images.unsplash.com/photo-1618477462146-050d2767eac4"
        ),

        Article(
            author = "TechCrunch",
            title = "Apple releases iOS 18 with major updates and new features",
            description = "",
            content = "",
            publishedAt = "2026-05-11",
            source = Source(
                id = "techcrunch",
                name = "TechCrunch"
            ),
            url = "https://techcrunch.com",
            urlToImage = "https://images.unsplash.com/photo-1511707171634-5f897ff02aa9"
        ),

        Article(
            author = "CNN",
            title = "US government avoids shutdown after last minute deal",
            description = "",
            content = "",
            publishedAt = "2026-05-11",
            source = Source(
                id = "cnn",
                name = "CNN"
            ),
            url = "https://cnn.com",
            urlToImage = "https://images.unsplash.com/photo-1574280363402-2f672940b871"
        ),

        Article(
            author = "ESPN",
            title = "Neeraj Chopra wins gold in javelin throw at Diamond League",
            description = "",
            content = "",
            publishedAt = "2026-05-11",
            source = Source(
                id = "espn",
                name = "ESPN"
            ),
            url = "https://espn.com",
            urlToImage = "https://images.unsplash.com/photo-1547347298-4074fc3086f0"
        )
    )

    val previewState = BookMarkState(
        articleList = sampleArticles
    )

    NewsyTheme {

        BookMarkScreen(
            state = previewState,
            navigateToDetails = {}
        )
    }
}


