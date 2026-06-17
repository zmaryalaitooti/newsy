package com.ahmadmaaz1.newsy.presentatoin.newscategory

import android.content.res.Configuration
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.BusinessCenter
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.Memory
import androidx.compose.material.icons.rounded.Movie
import androidx.compose.material.icons.rounded.Public
import androidx.compose.material.icons.rounded.Science
import androidx.compose.material.icons.rounded.SmartToy
import androidx.compose.material.icons.rounded.SportsSoccer
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ahmadmaaz1.newsy.domain.model.NewsCategory
import com.ahmadmaaz1.newsy.presentatoin.newscategory.component.CategoryChipsRow

@Composable
fun NewsCategoryScreen(
    onCategoryChange: (NewsCategory) -> Unit,
    selectedCategory: NewsCategory,
) {

    val isDark = isSystemInDarkTheme()

    val textColor = if (isDark) Color.White else Color.Black

    val categories = listOf(
        NewsCategory(
            "All",
            "general",
            Icons.Rounded.Public
        ),

        NewsCategory(
            "Business",
            "business",
            Icons.Rounded.BusinessCenter
        ),

        NewsCategory(
            "Technology",
            "technology",
            Icons.Rounded.Memory
        ),

        NewsCategory(
            "Sports",
            "sports",
            Icons.Rounded.SportsSoccer
        ),

        NewsCategory(
            "Health",
            "health",
            Icons.Rounded.Favorite
        ),

        NewsCategory(
            "Science",
            "science",
            Icons.Rounded.Science
        ),

        NewsCategory(
            "Entertainment",
            "entertainment",
            Icons.Rounded.Movie
        ),

        NewsCategory(
            "AI",
            "ai",
            Icons.Rounded.SmartToy
        )
    )



    Column(
        modifier = Modifier
            .padding(vertical = 12.dp)
    ) {

        Spacer(modifier = Modifier.height(14.dp))

        CategoryChipsRow(
            categories = categories,
            selected = selectedCategory,
            onSelected = {
                onCategoryChange.invoke(it)
            }
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Selected: ${selectedCategory.title}",
            modifier = Modifier.padding(horizontal = 16.dp),
            style = MaterialTheme.typography.titleMedium,
            color = textColor

        )
    }
}


@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun NewsCategoryPreview() {
    NewsCategoryScreen({}, NewsCategory("","",Icons.Rounded.Public))
}