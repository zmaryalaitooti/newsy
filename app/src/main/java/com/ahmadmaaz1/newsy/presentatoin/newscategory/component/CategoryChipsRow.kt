package com.ahmadmaaz1.newsy.presentatoin.newscategory.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ahmadmaaz1.newsy.domain.model.NewsCategory

@Composable
fun CategoryChipsRow(
    categories: List<NewsCategory>,
    selected: NewsCategory,
    onSelected: (NewsCategory) -> Unit
) {

    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) {

        items(categories) { category ->

            val isSelected = category == selected

            FilterChip(
                selected = isSelected,
                onClick = {
                    onSelected(category)
                },

                label = {

                    Text(
                        text = category.title,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium,
                        maxLines = 1
                    )
                },

                leadingIcon = {

                    Icon(
                        imageVector = category.icon,
                        contentDescription = category.title,
                        modifier = Modifier.size(18.dp)
                    )
                },

                shape = RoundedCornerShape(50),

                border = null,

                elevation = FilterChipDefaults.filterChipElevation(
                    elevation = if (isSelected) 4.dp else 0.dp
                ),

                colors = FilterChipDefaults.filterChipColors(

                    selectedContainerColor = Color(0xFFE53935),

                    selectedLabelColor = Color.White,

                    selectedLeadingIconColor = Color.White,

                    containerColor = Color(0xFFF3F4F6),

                    labelColor = Color.Black,

                    iconColor = Color.Gray
                ),

                modifier = Modifier.height(42.dp)
            )
        }
    }
}