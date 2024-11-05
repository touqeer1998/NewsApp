package com.loc.newsapp.presentation.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.loc.newsapp.domain.model.Article
import com.loc.newsapp.presentation.onboarding.components.utils.Dimens.ExtraSmallPadding2
import com.loc.newsapp.presentation.onboarding.components.utils.Dimens.MediumPadding

@Composable
fun ArticlesList(
    modifier: Modifier, articles: LazyPagingItems<Article>, onArticleClick: (Article) -> Unit
) {
    val handlePagingResult = HandlePagingResults(articles = articles)
    if (handlePagingResult) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.spacedBy(
                MediumPadding
            ), contentPadding = PaddingValues(all = ExtraSmallPadding2)
        ) {
            items(articles.itemCount) {
                articles[it]?.let { article ->
                    ArticleCard(article = article, onArticleClick = {
                        onArticleClick(article)
                    })
                }
            }
        }
    }
}

@Composable
fun HandlePagingResults(
    articles: LazyPagingItems<Article>
): Boolean {
    val loadState = articles.loadState
    val error = when {
        loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
        loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
        loadState.append is LoadState.Error -> loadState.append as LoadState.Error
        else -> null
    }

    return when {
        loadState.refresh is LoadState.Loading -> {
            ShimmerEffect()
            false
        }

        error != null -> {
            EmptyScreen()
            false
        }

        else -> true
    }
}

@Composable
private fun ShimmerEffect() {
    Column(verticalArrangement = Arrangement.spacedBy(MediumPadding)) {
        repeat(10) {
            ArticleShimmerEffect(modifier = Modifier.padding(horizontal = MediumPadding))
        }
    }
}