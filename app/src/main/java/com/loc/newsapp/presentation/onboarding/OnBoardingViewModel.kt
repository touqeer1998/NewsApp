package com.loc.newsapp.presentation.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.loc.newsapp.domain.usecases.AppEntryUserCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor(
    private val appEntryUserCases: AppEntryUserCases
) : ViewModel() {

    fun onEvent(event: OnBoardingEvent) {
        when (event) {
            is OnBoardingEvent.ReadAppEntry -> {
                readAppEntry()
            }

            is OnBoardingEvent.SaveAppEntry -> {
                saveAppEntry()
            }
        }
    }

    private fun readAppEntry() {
        appEntryUserCases.readAppEntry.invoke()
    }

    private fun saveAppEntry() = viewModelScope.launch {
        appEntryUserCases.saveAppEntry.invoke()
    }

}