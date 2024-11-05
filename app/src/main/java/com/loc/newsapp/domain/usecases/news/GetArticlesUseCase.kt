package com.loc.newsapp.domain.usecases.news

import com.loc.newsapp.domain.model.Article
import com.loc.newsapp.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class GetArticlesUseCase(private val newsRepository: NewsRepository) {
    operator fun invoke(): Flow<List<Article>> {
        return newsRepository.getAllArticles()
    }
}