package com.ahmadmaaz1.newsy.presentatoin.onboarding

import androidx.annotation.DrawableRes
import com.ahmadmaaz1.newsy.R

data class Page(val title: String, val desc: String, @DrawableRes val imageInt: Int)

val pageList =
    listOf(

        Page(
            title = "Hello this is the news app",
            desc = "You can also animate the logo or text using AnimatedVisibility, animateFloatAsState, or Lottie.\n"+
                    "Would you like a sample with an animation or logo fade-in".repeat(3),
            R.drawable.onboarding1
        ), Page(
            title = "Hello this is the news app",

            desc = "You can also animate the logo or text using AnimatedVisibility, animateFloatAsState, or Lottie.\n" +
                    "Would you like a sample with an animation or logo fade-in".repeat(3),
            R.drawable.onboarding2
        ), Page(
            title = "Hello this is the news app",
            desc = "You can also animate the logo or text using AnimatedVisibility, animateFloatAsState, or Lottie.\n" +
                    "Would you like a sample with an animation or logo fade-in".repeat(3),
            R.drawable.onboarding3
        )

    )


