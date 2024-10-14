package com.loc.newsapp.presentation.onboarding.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.loc.newsapp.R
import com.loc.newsapp.presentation.onboarding.components.utils.Dimens.LargePadding
import com.loc.newsapp.presentation.onboarding.components.utils.Dimens.MediumPadding
import com.loc.newsapp.presentation.onboarding.models.Page
import com.loc.newsapp.presentation.onboarding.models.pages
import com.loc.newsapp.ui.theme.NewsAppTheme

@Composable
fun OnBoardingPage(
    modifier: Modifier, page: Page
) {
    Column(
        modifier = modifier
    ) {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(fraction = 0.6f),
            painter = painterResource(id = page.image),
            contentDescription = page.description,
            contentScale = ContentScale.FillWidth
        )
        Spacer(modifier = Modifier.height(MediumPadding))
        Text(
            text = page.title,
            modifier = Modifier.padding(horizontal = LargePadding),
            style = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.Bold),
            color = colorResource(id = R.color.display_small)
        )
        Spacer(modifier = Modifier.height(MediumPadding))
        Text(
            text = page.description,
            modifier = Modifier.padding(horizontal = LargePadding),
            style = MaterialTheme.typography.bodyMedium,
            color = colorResource(id = R.color.text_medium)
        )
    }
}

@Preview(showBackground = true)
@Preview(uiMode = UI_MODE_NIGHT_YES, showBackground = true, backgroundColor = 0xFF000000)
@Composable
fun OnBoardingPagePreview() {
    NewsAppTheme {
        OnBoardingPage(modifier = Modifier, page = pages[0])
    }
}