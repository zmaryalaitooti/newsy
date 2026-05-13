package com.ahmadmaaz1.newsy.presentatoin.search

import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ahmadmaaz1.newsy.domain.model.Article

@Composable
fun SearchScreen(
    state: NewsSearchState,
    event: (SearchEvent) -> Unit,
    navigateToDetails: (Article) -> Unit
) {

    val isDark = isSystemInDarkTheme()

    val backgroundColor = if (isDark) Color(0xFF121212) else Color.White
    val textColor = if (isDark) Color.White else Color.Black
    val secondaryText = if (isDark) Color(0xFFB0B0B0) else Color.Gray
    val fieldBorder = if (isDark) Color(0xFF2C2C2C) else Color(0xFFE0E0E0)
    val dividerColor = if (isDark) Color(0xFF2A2A2A) else Color(0xFFEDEDED)
    val cardColor = if (isDark) Color(0xFF1E1E1E) else Color.White

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .statusBarsPadding()
            .padding(horizontal = 16.dp)
    ) {

        Spacer(modifier = Modifier.height(10.dp))

        // Top Bar
        Box(
            modifier = Modifier.fillMaxWidth()
        ) {

            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = null,
                tint = textColor,
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .size(22.dp)
            )

            Text(
                text = "Search",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.Center),
                color = textColor
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Search Field
        OutlinedTextField(
            value = state.search,
            onValueChange = {
                event(SearchEvent.updateSearchNews(it))
            },
            placeholder = {
                Text(
                    text = "Search news, topics, sources",
                    color = secondaryText
                )
            },
            singleLine = true,
            shape = RoundedCornerShape(14.dp),
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = cardColor,
                unfocusedContainerColor = cardColor,
                focusedBorderColor = fieldBorder,
                unfocusedBorderColor = fieldBorder,
                focusedTextColor = textColor,
                unfocusedTextColor = textColor,
                cursorColor = textColor
            ),
            trailingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = null,
                    tint = secondaryText
                )
            }
        )

        Spacer(modifier = Modifier.height(28.dp))

        // Recent Searches
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Text(
                text = "Recent Searches",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = textColor
            )

            Text(
                text = "Clear",
                color = Color.Red,
                fontWeight = FontWeight.Medium
            )
        }

        Spacer(modifier = Modifier.height(14.dp))

        val recentList = listOf(
            "Artificial Intelligence",
            "US Election 2024",
            "Stock Market Today",
            "SpaceX Launch",
            "Bitcoin Price"
        )

        recentList.forEach {

            SearchHistoryItem(
                text = it,
                textColor = textColor,
                iconColor = secondaryText
            )

            Spacer(modifier = Modifier.height(16.dp))
        }

        Divider(
            modifier = Modifier.padding(vertical = 20.dp),
            color = dividerColor
        )

        Spacer(modifier = Modifier.height(18.dp))
    }
}

@Composable
fun SearchHistoryItem(
    text: String,
    textColor: Color,
    iconColor: Color
) {

    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {

        Icon(
            imageVector = Icons.Default.DateRange,
            contentDescription = null,
            tint = iconColor,
            modifier = Modifier.size(20.dp)
        )

        Spacer(modifier = Modifier.width(12.dp))

        Text(
            text = text,
            fontSize = 16.sp,
            color = textColor
        )
    }
}
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SearchScreenPreview() {

    val fakeState = NewsSearchState(
        search = ""
    )

    SearchScreen(
        state = fakeState,
        event = {},
        navigateToDetails = {}
    )
}