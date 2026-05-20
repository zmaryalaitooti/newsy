package com.ahmadmaaz1.newsy.domain.model

import androidx.compose.ui.graphics.vector.ImageVector

data class NewsCategory(
    val title: String,
    val apiValue: String,
    val icon: ImageVector
)