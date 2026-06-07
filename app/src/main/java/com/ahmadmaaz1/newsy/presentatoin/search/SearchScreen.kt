package com.ahmadmaaz1.newsy.presentatoin.search

import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import com.ahmadmaaz1.newsy.domain.model.Article
import com.ahmadmaaz1.newsy.domain.model.SearchModel
import com.ahmadmaaz1.newsy.presentatoin.home.componet.NewsCard
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged

@OptIn(FlowPreview::class)
@Composable
fun SearchScreen(
    viewModel: NewsSearchViewModel,
    state: NewsSearchState,
    event: (SearchEvent) -> Unit,
    navigateToDetails: (Article) -> Unit,
    onBackClick: () -> Unit = {}
) {

    val isDark = isSystemInDarkTheme()

    val backgroundColor = if (isDark) Color(0xFF121212) else Color.White
    val textColor = if (isDark) Color.White else Color.Black
    val secondaryText = if (isDark) Color(0xFFB0B0B0) else Color.Gray
    val fieldBorder = if (isDark) Color(0xFF2C2C2C) else Color(0xFFE0E0E0)
    val dividerColor = if (isDark) Color(0xFF2A2A2A) else Color(0xFFEDEDED)
    val cardColor = if (isDark) Color(0xFF1E1E1E) else Color.White

    val articles = state.articles?.collectAsLazyPagingItems()

    // 🔥 Suggestions (simple local filter OR from ViewModel)
    val allSuggestions = viewModel.suggestions.collectAsLazyPagingItems()

    val suggestions = remember(
        state.search,
        allSuggestions.itemSnapshotList.items
    ) {

        if (state.search.isBlank()) {

            allSuggestions.itemSnapshotList.items

        } else {

            allSuggestions.itemSnapshotList.items.filter {
                it.search.contains(
                    state.search.trim(),
                    ignoreCase = true
                )
            }
        }
    }
    var showSuggestions by rememberSaveable  { mutableStateOf(true) }
//
//    LaunchedEffect(Unit) {
//        snapshotFlow { state.search }
//            .debounce(500)
//            .collect { query ->
//                if (query.isNotBlank()) {
//                    viewModel.insertSuggestion(query)
//                    event(SearchEvent.searchEvent)
//                }
//            }
//    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .statusBarsPadding()
            .padding(horizontal = 16.dp)
    ) {

        Spacer(modifier = Modifier.height(10.dp))

        // ---------------- TOP BAR ----------------
        Box(modifier = Modifier.fillMaxWidth()) {

            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = null,
                tint = textColor,
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .size(22.dp)
                    .clickable { onBackClick() }
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

        // ---------------- SEARCH FIELD ----------------
        OutlinedTextField(
            value = state.search,
            onValueChange = {
                event(SearchEvent.UpdateSearchNews(it))
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
                    modifier = Modifier.clickable(enabled = true, onClick = {
                        showSuggestions = false
                        event(SearchEvent.UpdateSearchNews(state.search))
                        event(SearchEvent.searchEvent)
                        viewModel.insertSuggestion(state.search)
                    }),
                    imageVector = Icons.Default.Search,
                    contentDescription = null,
                    tint = secondaryText
                )
            }
        )

        // ---------------- 🔥 SUGGESTIONS ----------------
        if (showSuggestions)  {

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "Suggestions",
                color = secondaryText,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(8.dp))

            suggestions.forEach { suggestion ->

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            showSuggestions = false
                            event(SearchEvent.UpdateSearchNews(suggestion.search))
                            event(SearchEvent.searchEvent)
                            viewModel.insertSuggestion(suggestion.search)
                        }
                        .padding(vertical = 10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = null,
                        tint = secondaryText,
                        modifier = Modifier.size(18.dp)
                    )

                    Spacer(modifier = Modifier.width(10.dp))

                    Text(
                        text = suggestion.search,
                        color = textColor
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // ---------------- RESULTS ----------------

        if (articles != null) {

            when {

                articles.loadState.refresh is LoadState.Loading -> {

                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }

                articles.itemCount == 0 && state.search.isNotBlank() -> {

                    Text(
                        text = "No results found",
                        color = secondaryText,
                        modifier = Modifier.padding(top = 20.dp)
                    )
                }

                else -> {

                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {

                        items(
                            count = articles.itemCount,
                            key = articles.itemKey()
                        ) { index ->

                            val article = articles[index]

                            article?.let {
                                NewsCard(
                                    article = it,
                                    navigatorToDetail = navigateToDetails
                                )
                            }
                        }

                        if (articles.loadState.append is LoadState.Loading) {

                            item {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(16.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    CircularProgressIndicator()
                                }
                            }
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(18.dp))
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SearchScreenPreview() {

    val fakeState = NewsSearchState(
        search = ""
    )

//    SearchScreen(
//        viewModel = viewModel,
//        state = fakeState,
//        event = {},
//        navigateToDetails = {}
//    )
}