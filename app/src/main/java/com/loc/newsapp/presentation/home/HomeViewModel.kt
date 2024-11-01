package com.loc.newsapp.presentation.home

import androidx.lifecycle.ViewModel
import com.loc.newsapp.domain.usecases.news.NewsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    newsUserCase: NewsUseCases
) : ViewModel() {

    val news = newsUserCase.getNewsUseCase(
        source = listOf(
            "ary-news",
            "bbc-news",
            "bbc-sport",
            "abc-news",
            "al-jazeera-english",
            "cnn",
            "fox-news",
            "aftenposten"
        )
    )

}