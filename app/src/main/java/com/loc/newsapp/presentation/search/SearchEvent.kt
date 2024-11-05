package com.loc.newsapp.presentation.search

sealed class SearchEvent {
    data class UpdateSearchQuery(val query: String) : SearchEvent()

    object SearchNews : SearchEvent()
}