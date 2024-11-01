package com.loc.newsapp.domain.usecases.app_entry

data class AppEntryUseCase(
    val readAppEntry: ReadAppEntry, val saveAppEntry: SaveAppEntry
)