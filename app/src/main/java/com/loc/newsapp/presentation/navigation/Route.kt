package com.loc.newsapp.presentation.navigation

sealed class Route(val route: String) {
    object OnBoardingScreen : Route("onBoardingScreen")
    object HomeScreen : Route("homeScreen")
    object SearchScreen : Route("searchScreen")
    object BookmarkScreen : Route("bookmarkScreen")
    object DetailsScreen : Route("detailsScreen")
    object AppStartNavigation : Route("appStartNavigation")
    object NewsNavigation : Route("newsNavigation")
    object NewsNavigatorScreen : Route("newsNavigatorScreen")
}