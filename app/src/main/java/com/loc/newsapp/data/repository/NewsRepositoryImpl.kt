package com.loc.newsapp.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.loc.newsapp.data.local.NewsDao
import com.loc.newsapp.data.remote.NewsApi
import com.loc.newsapp.data.remote.NewsPagingSource
import com.loc.newsapp.data.remote.SearchNewsPagingSource
import com.loc.newsapp.domain.model.Article
import com.loc.newsapp.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class NewsRepositoryImpl(private val newApi: NewsApi, private val newsDao: NewsDao) :
    NewsRepository {
    override fun getAllNews(source: List<String>): Flow<PagingData<Article>> {
        return Pager(config = PagingConfig(pageSize = 10), pagingSourceFactory = {
            NewsPagingSource(
                newsApi = newApi, source = source.joinToString(separator = ",")
            )
        }).flow
    }

    override fun searchNews(query: String, sources: List<String>): Flow<PagingData<Article>> {
        return Pager(config = PagingConfig(pageSize = 10), pagingSourceFactory = {
            SearchNewsPagingSource(
                newsApi = newApi,
                searchQuery = query,
                sources = sources.joinToString(separator = ",")
            )
        }).flow
    }

    override suspend fun upsert(article: Article) {
        newsDao.upsert(article)
    }

    override suspend fun delete(article: Article) {
        newsDao.delete(article)
    }

    override suspend fun findArticleByUrl(url: String): Article? {
        return newsDao.findArticleByUrl(url)
    }

    override fun getAllArticles(): Flow<List<Article>> {
        return newsDao.getAllNews()
    }
}