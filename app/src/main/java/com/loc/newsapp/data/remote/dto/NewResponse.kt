package com.loc.newsapp.data.remote.dto

import com.loc.newsapp.domain.model.Article

data class NewResponse(
    val articles: List<Article>, val status: String, val totalResults: Int
)