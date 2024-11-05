package com.loc.newsapp.domain.usecases.news

import com.loc.newsapp.domain.model.Article
import com.loc.newsapp.domain.repository.NewsRepository

class UpsertArticleUseCase(private val newsRepository: NewsRepository) {
    suspend operator fun invoke(article: Article) {
        newsRepository.upsert(article)
    }
}