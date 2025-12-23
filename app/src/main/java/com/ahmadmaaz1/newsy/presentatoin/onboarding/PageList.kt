package com.ahmadmaaz1.newsy.presentatoin.onboarding

import androidx.annotation.DrawableRes
import com.ahmadmaaz1.newsy.R

data class Page(val title: String, val desc: String, @DrawableRes val imageInt: Int)

val pageList =
    listOf(

        Page(
            title = "Stay Informed, Instantly",
            desc = "Get the latest headlines and stories that matter — all in one place. Newsy keeps you updated with real-time news from trusted sources around the world.",
            imageInt =  R.drawable.onboarding1,
        ),
        Page(
            title = "Your News, Your Way",
            desc = "Customize your feed to match your interests — from politics and sports to tech and entertainment. Follow what you care about and skip what you don’t.",
            imageInt = R.drawable.onboarding2
        ),
        Page(
            title = "Read in Comfort, Anytime",
            desc = "Enjoy a clean reading experience in both light and dark modes. Save articles, share stories, and stay connected — the smart way to read the news with Newsy.",
            imageInt = R.drawable.onboarding3
        )


    )


