package com.ahmadmaaz1.newsy.presentatoin.component

import android.content.res.Configuration
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.icu.text.StringSearch
import android.util.Log
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.key
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ahmadmaaz1.newsy.R
import com.ahmadmaaz1.newsy.ui.theme.NewsyTheme
import kotlinx.coroutines.delay

private const val TAG = "SearchBar"

@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    onclick: () -> Unit,
    text: String,
    readOnly: Boolean,
    onValueChange: (String) -> Unit,
    onSearch: () -> Unit
) {
    val interactionResource = remember { MutableInteractionSource() }
    val isClick = interactionResource.collectIsPressedAsState().value

    val focusRequester = remember { FocusRequester() }
    val keyboardController = LocalSoftwareKeyboardController.current

    // 🔥 Automatically request focus when entering the screen
    LaunchedEffect(Unit) {
        delay(200) // small delay for smoother UI transition
        focusRequester.requestFocus()
        keyboardController?.show()
    }

    LaunchedEffect(isClick) {
        Log.d(TAG, "SearchBar: launchEffect out site in if")
        if (isClick) {
            Log.d(TAG, "SearchBar: launchEffect in site in if")

            onclick.invoke()
        }
    }

    Box(modifier.clickable(enabled = true, onClick = {
        onclick.invoke()
        Log.d(TAG, "SearchBar: onclicked")
    }
    )
    )
    {
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .SearchBarBorder()
                .focusRequester(focusRequester),
            value = text,
            onValueChange = {
                onValueChange.invoke(it)
                Log.d(TAG, "SearchBar: text is $text")
            },
            readOnly = readOnly,
            leadingIcon = {
                IconButton(onClick = { onSearch.invoke()
                    Log.d(TAG, "SearchBar: leading icon clicked ")}) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search"
                    )
                }

            },
            placeholder = {
                Text(
                    text = "Search here",
                    style = MaterialTheme.typography.bodyMedium,
                    color = colorResource(R.color.teal_200)//placeHolderColor
                )
            },
            shape = MaterialTheme.shapes.medium,
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color(0xFFF5F5F5),
                unfocusedContainerColor = Color(0xFFF5F5F5),
                focusedTextColor = if (isSystemInDarkTheme()) Color.White else Color.Black,
                unfocusedTextColor = if (isSystemInDarkTheme()) Color.White else Color.Black,
                cursorColor = if (isSystemInDarkTheme()) Color.White else Color.Black,
                disabledIndicatorColor = Color.Transparent,
                errorIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            singleLine = true,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(onSearch = {
                onSearch()
            }),
            textStyle = MaterialTheme.typography.bodySmall,
            interactionSource = interactionResource
        )
    }
}

fun Modifier.SearchBarBorder() = composed {
    if (!isSystemInDarkTheme()) {
        border(
            width = 1.dp,
            color = Color.Black,
            shape = MaterialTheme.shapes.medium
        )
    } else {
        this
    }
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun SearchBarPreview() {
    NewsyTheme {
        SearchBar(
            text = "Search",
            readOnly = false,
            onclick = {},
            onValueChange = {},
        ) { }
    }
}