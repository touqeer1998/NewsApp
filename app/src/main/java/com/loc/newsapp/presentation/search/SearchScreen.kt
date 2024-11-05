package com.loc.newsapp.presentation.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.compose.collectAsLazyPagingItems
import com.loc.newsapp.domain.model.Article
import com.loc.newsapp.presentation.common.ArticlesList
import com.loc.newsapp.presentation.common.SearchBar
import com.loc.newsapp.presentation.onboarding.components.utils.Dimens.MediumPadding

@Composable
fun SearchScreen(
    state: SearchState,
    event: (SearchEvent) -> Unit,
    navigateToDetails: (Article) -> Unit,
) {
    Column(
        modifier = Modifier
            .padding(
                start = MediumPadding, end = MediumPadding, top = MediumPadding
            )
            .statusBarsPadding()
            .fillMaxSize()
    ) {
        SearchBar(text = state.query,
            readOnly = false,
            onValueChange = { event(SearchEvent.UpdateSearchQuery(it)) },
            onSearch = { event(SearchEvent.SearchNews) })

        Spacer(modifier = Modifier.height(MediumPadding))

        state.article?.let {
            val articles = it.collectAsLazyPagingItems()
            ArticlesList(modifier = Modifier,
                articles = articles,
                onArticleClick = { article -> navigateToDetails(article) })
        }
    }
}