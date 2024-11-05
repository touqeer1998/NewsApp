package com.loc.newsapp.presentation.newsNavigator

import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.loc.newsapp.R
import com.loc.newsapp.domain.model.Article
import com.loc.newsapp.presentation.bookmark.BookMarkScreen
import com.loc.newsapp.presentation.bookmark.BookMarkViewModel
import com.loc.newsapp.presentation.details.DetailsEvent
import com.loc.newsapp.presentation.details.DetailsScreen
import com.loc.newsapp.presentation.details.DetailsViewModel
import com.loc.newsapp.presentation.home.HomeScreen
import com.loc.newsapp.presentation.home.HomeViewModel
import com.loc.newsapp.presentation.navigation.Route
import com.loc.newsapp.presentation.newsNavigator.components.NavigationItems
import com.loc.newsapp.presentation.newsNavigator.components.NewsBottomNavigation
import com.loc.newsapp.presentation.search.SearchScreen
import com.loc.newsapp.presentation.search.SearchViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsNavigator() {
    val bottomNavigationItems = remember {
        listOf(
            NavigationItems(icon = R.drawable.ic_home, name = "Home"),
            NavigationItems(icon = R.drawable.ic_search, name = "Search"),
            NavigationItems(icon = R.drawable.ic_bookmark, name = "Bookmark"),
        )
    }

    val navController = rememberNavController()
    val backStackState = navController.currentBackStackEntryAsState().value
    var selectedItem by rememberSaveable {
        mutableIntStateOf(0)
    }
    selectedItem = remember(key1 = backStackState) {
        when (backStackState?.destination?.route) {
            Route.HomeScreen.route -> 0
            Route.SearchScreen.route -> 1
            Route.BookmarkScreen.route -> 2
            else -> 0
        }
    }
    val isBottomNavigationVisible = remember(key1 = backStackState) {
        backStackState?.destination?.route == Route.HomeScreen.route || backStackState?.destination?.route == Route.SearchScreen.route || backStackState?.destination?.route == Route.BookmarkScreen.route
    }

    Scaffold(modifier = Modifier.fillMaxWidth(), bottomBar = {
        if (isBottomNavigationVisible) {
            NewsBottomNavigation(items = bottomNavigationItems,
                selectedItem = selectedItem,
                onClick = { index ->
                    when (index) {
                        0 -> navigateToTab(
                            navController = navController, route = Route.HomeScreen.route
                        )

                        1 -> navigateToTab(
                            navController = navController, route = Route.SearchScreen.route
                        )

                        2 -> navigateToTab(
                            navController = navController, route = Route.BookmarkScreen.route
                        )
                    }
                })
        }
    }) {
        val bottomPadding = it.calculateBottomPadding()
        NavHost(
            navController = navController,
            startDestination = Route.HomeScreen.route,
            modifier = Modifier.padding(bottom = bottomPadding)
        ) {
            composable(route = Route.HomeScreen.route) {
                val viewModel: HomeViewModel = hiltViewModel()
                val articles = viewModel.news.collectAsLazyPagingItems()
                HomeScreen(articles = articles, navigateToSearch = {
                    navigateToTab(
                        navController = navController, route = Route.SearchScreen.route
                    )
                }, navigateToDetails = { article ->
                    navigateToDetails(
                        navController = navController, article = article
                    )
                })
            }

            composable(route = Route.SearchScreen.route) {
                val viewModel: SearchViewModel = hiltViewModel()
                val state = viewModel.state.value
                OnBackClickStateSaver(navController = navController)
                SearchScreen(state = state,
                    event = viewModel::onEvent,
                    navigateToDetails = { article ->
                        navigateToDetails(
                            navController = navController, article = article
                        )
                    })
            }

            composable(route = Route.DetailsScreen.route) {
                navController.previousBackStackEntry?.savedStateHandle?.get<Article>("article")
                    ?.let { article ->
                        val viewModel: DetailsViewModel = hiltViewModel()
                        if (viewModel.sideEffect != null) {
                            Toast.makeText(
                                LocalContext.current, viewModel.sideEffect, Toast.LENGTH_SHORT
                            ).show()
                            viewModel.onEvent(DetailsEvent.RemoveSideEffect)
                        }
                        DetailsScreen(article = article,
                            event = viewModel::onEvent,
                            navigateUp = { navController.navigateUp() })
                    }
            }

            composable(route = Route.BookmarkScreen.route) {
                val viewModel: BookMarkViewModel = hiltViewModel()
                val state = viewModel.state.value
                BookMarkScreen(state = state, navigateToDetails = { article ->
                    navigateToDetails(
                        navController = navController, article = article
                    )
                })
            }
        }
    }
}


private fun navigateToTab(navController: NavController, route: String) {
    navController.navigate(route) {
        navController.graph.startDestinationRoute?.let { screenRounte ->
            popUpTo(screenRounte) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }
}

private fun navigateToDetails(navController: NavController, article: Article) {
    navController.currentBackStackEntry?.savedStateHandle?.set("article", article)
    navController.navigate(
        route = Route.DetailsScreen.route
    )
}

@Composable
fun OnBackClickStateSaver(navController: NavController) {
    BackHandler(true) {
        navigateToTab(
            navController = navController, route = Route.HomeScreen.route
        )
    }
}