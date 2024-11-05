package com.loc.newsapp.presentation.bookmark

import com.loc.newsapp.domain.model.Article

data class BookMarkState(
    val bookmarks: List<Article> = emptyList()
)
