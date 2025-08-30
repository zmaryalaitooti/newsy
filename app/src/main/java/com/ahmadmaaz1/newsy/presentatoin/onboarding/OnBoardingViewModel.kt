package com.ahmadmaaz1.newsy.presentatoin.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahmadmaaz1.newsy.domain.usecause.appEntry.AppEntryUseCause
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor(val appEntryUseCause: AppEntryUseCause) :
    ViewModel() {

    fun onEvent(event: OnBoardingEvent) {
        when (event) {
            is OnBoardingEvent.SaveAppEntry -> {
                saveAppEntry()
            }
        }
    }

    private fun saveAppEntry() {
        viewModelScope.launch {
            appEntryUseCause.appSaveAppEntry.invoke()
        }
    }

}