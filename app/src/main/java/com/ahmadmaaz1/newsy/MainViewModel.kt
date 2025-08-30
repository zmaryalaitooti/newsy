package com.ahmadmaaz1.newsy

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahmadmaaz1.newsy.domain.usecause.appEntry.AppEntryUseCause
import com.ahmadmaaz1.newsy.presentatoin.navgraph.Route
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject() constructor(val appEntryUseCause: AppEntryUseCause): ViewModel() {

    var splashScreen by mutableStateOf(true)
        private set

    var startDestination by mutableStateOf(Route.AppStarNavigation.route)
        private set

    init {
        appEntryUseCause.readAppEntry().onEach {
            if (it){
                startDestination = Route.NewsNavigation.route
            }
            delay(150)
            splashScreen = false

        }.launchIn(viewModelScope)
    }
}