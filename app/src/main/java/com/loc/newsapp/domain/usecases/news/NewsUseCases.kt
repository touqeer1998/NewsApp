package com.loc.newsapp.domain.usecases.news

data class NewsUseCases(
    val getNewsUseCase: GetNewUserCase,
    val searchNewsUseCase: SearchNewsUseCase,
    val upsertArticleUseCase: UpsertArticleUseCase,
    val deleteArticleUseCase: DeleteArticleUseCase,
    val getArticlesUseCase: GetArticlesUseCase,
    val findArticleByUrlUseCase: FindArticleByUrlUseCase
)
