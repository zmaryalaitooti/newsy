package com.ahmadmaaz1.newsy.presentatoin.component

import android.content.res.Configuration
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Build
import androidx.annotation.ColorRes
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.Coil
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.request.ImageResult
import com.ahmadmaaz1.newsy.R
import com.ahmadmaaz1.newsy.domain.model.Article
import com.ahmadmaaz1.newsy.domain.model.Source
import com.ahmadmaaz1.newsy.presentatoin.component.getTimeAgo
import com.ahmadmaaz1.newsy.ui.theme.NewsyTheme
import java.time.Duration
import java.time.Instant

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ArticleCard(modifier: Modifier = Modifier, article: Article, onClick: () -> Unit) {
    val context = LocalContext.current
    Row(modifier = modifier
        .fillMaxWidth()
        .clickable(enabled = true, onClick = { onClick() }))
    {
        AsyncImage(
            modifier = Modifier
                .size(96.dp)
                .clip(shape = MaterialTheme.shapes.medium),
            model = ImageRequest.Builder(context).data(article.urlToImage).build(),
            contentDescription = null
        )

        Column(
            verticalArrangement = Arrangement.SpaceAround,
            modifier = Modifier.padding(horizontal = 3.dp).height(96.dp)
        ) {
            Text(
                text = article.title.toString().uppercase(),
                style = MaterialTheme.typography.labelLarge.copy(
                    color = MaterialTheme.colorScheme.secondary),
                overflow = TextOverflow.Ellipsis,
                maxLines = 1
            )

            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = article.source.name.toString(),
                    style = MaterialTheme.typography.labelMedium.copy(fontWeight = Bold),
                    color = colorResource(R.color.black)
                )

                Spacer(modifier = Modifier.size(6.dp))

                Icon(imageVector = Icons.Default.DateRange,contentDescription = null,
                    modifier = Modifier.size(11.dp),
                    tint = colorResource(R.color.black)
                    )
                Text(
                    text = getTimeAgo(isoDate = article.publishedAt.toString()),
                    style = MaterialTheme.typography.labelMedium,
                    color = colorResource(R.color.black)
                )            }
        }
    }
}




@RequiresApi(Build.VERSION_CODES.O)
private fun RowScope.getTimeAgo(isoDate: String): String {
    // Parse the input date
    val time = Instant.parse(isoDate)
    val now = Instant.now()

    // Calculate difference
    val duration = Duration.between(time, now)

    val seconds = duration.seconds
    val minutes = seconds / 60
    val hours = minutes / 60
    val days = hours / 24
    val months = days / 30
    val years = days / 365

    return when {
        seconds < 60 -> "${seconds}s ago"
        minutes < 60 -> "${minutes}m ago"
        hours < 24 -> "${hours}h ago"
        days < 30 -> "${days}d ago"
        months < 12 -> "${months}mo ago"
        else -> "${years}y ago"
    }
}


@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun ArticleCardPreview() {
    NewsyTheme {
        ArticleCard(
            Modifier, article = Article(author = null,content = null,description = null,publishedAt = "2h", source = Source(name = "khan", id = null), title = "new news ",url = "i", urlToImage = "dkji")
        ) { }
    }
}