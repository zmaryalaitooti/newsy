package com.ahmadmaaz1.newsy.presentatoin.newscategory

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
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

    val categories = listOf(
        NewsCategory("All", "general"),
        NewsCategory("Business", "business"),
        NewsCategory("Technology", "technology"),
        NewsCategory("Sports", "sports"),
        NewsCategory("Health", "health"),
        NewsCategory("Science", "science"),
        NewsCategory("Entertainment", "entertainment")
    )



    Column(
        modifier = Modifier
            .padding(vertical = 12.dp)
    ) {

        Text(
            text = "Top Stories",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

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
            style = MaterialTheme.typography.titleMedium
        )
    }
}


@Preview
@Composable
private fun NewsCategoryPreview() {
    NewsCategoryScreen({}, NewsCategory("",""))
}