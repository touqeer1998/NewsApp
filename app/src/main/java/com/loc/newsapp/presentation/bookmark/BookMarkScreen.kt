package com.loc.newsapp.presentation.bookmark

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.loc.newsapp.R
import com.loc.newsapp.presentation.common.ArticlesList
import com.loc.newsapp.presentation.navigation.Route
import com.loc.newsapp.presentation.onboarding.components.utils.Dimens.MediumPadding
import com.loc.newsapp.ui.theme.NewsAppTheme

@Composable
fun BookMarkScreen(
    state: BookMarkState, navigate: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .padding(start = MediumPadding, end = MediumPadding, top = MediumPadding)
    ) {
        Text(
            text = "Bookmark",
            style = MaterialTheme.typography.displayMedium.copy(fontWeight = FontWeight.Bold),
            color = colorResource(id = R.color.text_title)
        )

        Spacer(modifier = Modifier.height(MediumPadding))

        ArticlesList(modifier = Modifier, articles = state.bookmarks, onArticleClick = {
            navigate(Route.DetailsScreen.route)
        })
    }
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun BookMarkScreenPreview() {
    NewsAppTheme {
        Box(modifier = Modifier.background(color = MaterialTheme.colorScheme.background)) {
            BookMarkScreen(state = BookMarkState(), navigate = {})
        }
    }
}