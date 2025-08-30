package com.ahmadmaaz1.newsy.presentatoin.onboarding.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import com.ahmadmaaz1.newsy.presentatoin.onboarding.Dimension

@Composable
fun PageIndicator(
    modifier: Modifier = Modifier,
    pageSize: Int,
    selectedPage: Int,
    selectedPageColor: Color = MaterialTheme.colorScheme.primary,
    unSelectedColor : Color = Color.Gray
) {

    Row (modifier,Arrangement.SpaceBetween){
        repeat (pageSize){page->
            Box(
                Modifier.Companion.size(Dimension.IndicatorSize).clip(CircleShape).
            background(color = if (page == selectedPage) selectedPageColor else unSelectedColor))
        }

    }

}