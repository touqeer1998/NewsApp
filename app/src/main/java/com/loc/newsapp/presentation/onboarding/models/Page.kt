package com.loc.newsapp.presentation.onboarding.models

import androidx.annotation.DrawableRes
import com.loc.newsapp.R

data class Page(
    val title: String, val description: String, @DrawableRes val image: Int
)

val pages = listOf(
    Page(
        title = "Crypto News",
        description = "Get the information about where is digital currency is going right now.",
        image = R.drawable.onboarding1
    ),
    Page(
        title = "Beautiful places to visit",
        description = "Get the information about the most beautiful places to visit in the world.",
        image = R.drawable.onboarding2
    ),
    Page(
        title = "Best food for you",
        description = "You can find the best food for your taste.",
        image = R.drawable.onboarding3
    ),
)