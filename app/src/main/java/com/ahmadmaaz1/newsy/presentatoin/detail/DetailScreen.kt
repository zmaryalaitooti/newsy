package com.ahmadmaaz1.newsy.presentatoin.detail

import android.content.Intent
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ahmadmaaz1.newsy.domain.model.Article
import com.ahmadmaaz1.newsy.presentatoin.detail.component.DetailEvent
import com.ahmadmaaz1.newsy.presentatoin.detail.component.DetailTopAppBar
import androidx.core.net.toUri
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.ahmadmaaz1.newsy.R
import com.ahmadmaaz1.newsy.domain.model.Source

@RequiresApi(Build.VERSION_CODES.R)
@Composable
fun DetailScreen(
    viewmodel: DetailViewmodel,
    article: Article,
    event: (DetailEvent) -> Unit,
    navigateUp: () -> Unit,
) {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
    ) {

        DetailTopAppBar(
            isBookMar = viewmodel.sideEffect,
            onBackClick = navigateUp,
            onBrowseClick = {
                Intent(Intent.ACTION_VIEW).also {
                    it.data = article.url.toUri()
                    if (it.resolveActivity(context.packageManager) != null) {
                        context.startActivity(it)
                    }
                }
            },
            onShareClick = {
                Intent(Intent.ACTION_SEND).also {
                    it.putExtra(Intent.EXTRA_TIME, article.url)
                    it.type = "text/plain"
                    if (it.resolveActivity(context.packageManager) != null) {
                        context.startActivity(it)
                    }
                }
            },
            onBookMarkClick = { event(DetailEvent.SaveOrDeleteArticle(article)) }
        )

        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(
                start = 24.dp,//mediumpadding1
                end = 24.dp,//
                top = 24.dp
            ),
        ) {
            item{
                AsyncImage(
                    model = ImageRequest.Builder(context = context).data(article.urlToImage).build(),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(MaterialTheme.shapes.medium)
                        .height(250.dp),//articleImageHeight
                    contentScale = ContentScale.Crop
                )

                Spacer(Modifier.height(24.dp,)//medium padding1
                )

                Text(style = MaterialTheme.typography.displaySmall,
                    text = article.title.toString(),
                    color = colorResource(R.color.teal_700)//textTitle
                )

                Text(style = MaterialTheme.typography.bodyMedium,
                    text = article.content.toString(),
                    color = colorResource(R.color.black)//body
                )
            }

        }
    }

}


@RequiresApi(Build.VERSION_CODES.R)
@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun DetailScreenPreview() {
//    DetailScreen(
//        viewmodel = ,
//        article = Article(
//            author = null,
//            content = "ksjdkfjkdjskfjdsjfjkdjkfjk",
//            title = "news ",
//            publishedAt = null,
//            description = null,
//            source = Source(null,null),
//            urlToImage = "jdskfj",
//            url = "dkf"
//        ),
//        event = {},
//        navigateUp = {},
//    )
//
}