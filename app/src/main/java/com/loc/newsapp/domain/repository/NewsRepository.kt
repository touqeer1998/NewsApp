package com.loc.newsapp.domain.repository

import androidx.paging.PagingData
import com.loc.newsapp.domain.model.Article
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    fun getAllNews(source: List<String>): Flow<PagingData<Article>>

    fun searchNews(query: String, sources: List<String>): Flow<PagingData<Article>>

    suspend fun upsert(article: Article)

    suspend fun delete(article: Article)

    suspend fun findArticleByUrl(url: String): Article?

    fun getAllArticles(): Flow<List<Article>>
}