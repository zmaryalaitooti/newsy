package com.ahmadmaaz1.newsy.presentatoin.detail.component

import android.annotation.SuppressLint
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.ahmadmaaz1.newsy.R

@SuppressLint("ResourceAsColor")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailTopAppBar(
    isBookMar: String ,
    onBackClick: () -> Unit,
    onBrowseClick: () -> Unit,
    onShareClick: () -> Unit,
    onBookMarkClick: () -> Unit,
) {
    TopAppBar(title = { Text(text = "") }
        , modifier = Modifier.fillMaxWidth(),
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = Color.Transparent,
            actionIconContentColor = colorResource(R.color.purple_500),//body
            navigationIconContentColor = colorResource(R.color.purple_500),//body

        ),
        navigationIcon = {
            IconButton(onClick = onBackClick) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = null
                )
            }
        },
        actions = {
        IconButton(onClick = onShareClick) {

            Icon(
                imageVector = Icons.Default.Share,
                contentDescription = null
            )
        }
        IconButton(onClick = onBookMarkClick) {

            Icon(
                painter = painterResource(if (isBookMar == "") R.drawable.bookmark else R.drawable.bookmark_pin),
                contentDescription = null,
            )
        }
        IconButton(onClick = onBrowseClick) {
            Icon(
                painter = painterResource(R.drawable.browse),
                contentDescription = null
            )
        }
    })
}


@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Preview(showBackground = true)
@Composable
private fun DetailTopAppBarPreview() {
    Box(
        modifier = Modifier.background(MaterialTheme.colorScheme.background),
    ){
        DetailTopAppBar("",{},{},{},{})}
    }
