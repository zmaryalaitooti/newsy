package com.ahmadmaaz1.newsy.presentatoin.onboarding

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.ahmadmaaz1.newsy.R

@Composable
fun OnBoardingPage(modifier: Modifier = Modifier,page : Page) {

    Column(modifier = modifier.fillMaxWidth()) {
        Image(painter = painterResource(page.imageInt),contentDescription = null,modifier.fillMaxWidth().weight(1f), contentScale = ContentScale.Crop)
        Spacer(modifier.height(Dimension.MediumPadding1))
        Text(text = page.title, modifier = modifier.padding(horizontal = Dimension.MediumPadding2), style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
            color = colorResource(R.color.black)
        )
        Spacer(modifier.height(Dimension.MediumPadding1))
        Text(text = page.desc , modifier = modifier.padding(horizontal = Dimension.MediumPadding2), style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
            color = colorResource(R.color.black))

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