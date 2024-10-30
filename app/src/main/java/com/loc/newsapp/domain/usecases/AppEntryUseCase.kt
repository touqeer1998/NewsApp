package com.loc.newsapp.domain.usecases

data class AppEntryUseCase(
    val readAppEntry: ReadAppEntry, val saveAppEntry: SaveAppEntry
)