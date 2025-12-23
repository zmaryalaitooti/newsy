package com.ahmadmaaz1.newsy.presentatoin.onboarding

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ahmadmaaz1.newsy.R

@Composable
fun OnBoardingPage(
    modifier: Modifier = Modifier,
    page: Page
) {
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp
    val screenWidth = configuration.screenWidthDp.dp

    // Dynamic scaling factors based on screen size
    val imageHeight = screenHeight * 0.45f
    val horizontalPadding = screenWidth * 0.05f
    val titleFontSize = when {
        screenWidth < 360.dp -> 20.sp
        screenWidth < 480.dp -> 22.sp
        else -> 24.sp
    }
    val descFontSize = when {
        screenWidth < 360.dp -> 14.sp
        screenWidth < 480.dp -> 16.sp
        else -> 18.sp
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding( vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        // Responsive Image
        Image(
            painter = painterResource(page.imageInt),
            contentDescription = null,
            modifier = Modifier
                .height(imageHeight)
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp)),
            contentScale = ContentScale.Inside
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Responsive Title
        Text(
            text = page.title,
            modifier = Modifier.padding(horizontal = horizontalPadding),
            style = MaterialTheme.typography.titleLarge.copy(
                fontWeight = FontWeight.Bold,
                fontSize = titleFontSize
            ),
            color = colorResource(R.color.black)
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Responsive Description
        Text(
            text = page.desc,
            modifier = Modifier.padding(horizontal = horizontalPadding),
            style = MaterialTheme.typography.bodyLarge.copy(
                fontWeight = FontWeight.Normal,
                fontSize = descFontSize,
                lineHeight = descFontSize * 1.3
            ),
            textAlign = TextAlign.Center,
            color = colorResource(R.color.black)
        )
    }
}


@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Preview(showBackground = true)
@Composable
fun OnBoardingPreview() {
    OnBoardingPage(
       page =  pageList[0],
    )
}