package com.ahmadmaaz1.newsy.presentatoin.news_navigator.components

import android.annotation.SuppressLint
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.AssistChipDefaults.IconSize
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ahmadmaaz1.newsy.R
import com.ahmadmaaz1.newsy.presentatoin.navgraph.Route

@SuppressLint("ResourceAsColor")
@Composable
fun NewsBottomNavigation(
    listNavigator: List<NewsNavigate>,
    selected: Int,
    onItemClicked: (Int) -> Unit
) {

    NavigationBar(
        modifier = Modifier.fillMaxWidth(),
        containerColor = MaterialTheme.colorScheme.primary,
        tonalElevation = 10.dp
    ) {
        listNavigator.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = (selected == index),
                onClick = {onItemClicked.invoke(index)},
                icon = {
                    Column(horizontalAlignment = CenterHorizontally) {
                        Icon(
                            painter = painterResource(id = item.image), contentDescription = null,
                            Modifier.size(IconSize)
                        )
                        Spacer(Modifier.height(15.dp))//ExtraSmallPadding
                        Text(
                            text = item.text, style = MaterialTheme.typography.labelSmall,
                        )
                    }
                },
                colors = NavigationBarItemDefaults.colors(
//                    color = colorResource(R.color.teal_200)//placeHolderColor
                    selectedIconColor = Color(R.color.black),
                    selectedTextColor = Color(R.color.black),
                    unselectedIconColor = MaterialTheme.colorScheme.onSecondary, // body color
                    unselectedTextColor = MaterialTheme.colorScheme.onSecondary,
                )
            )
        }

    }
}

@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun NewsBottomNavigationPreview() {
    NewsBottomNavigation(
        listOf(
            NewsNavigate(R.drawable.onboarding3, text = "Home"),
            NewsNavigate(R.drawable.onboarding2, text = Route.Search.route),
            NewsNavigate(R.drawable.onboarding1, text = Route.DetailScreen.route),
        ),

        selected = 0,
        onItemClicked = {},
    ) 
}


data class NewsNavigate(@DrawableRes val image: Int, val text: String)