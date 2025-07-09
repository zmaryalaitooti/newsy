package com.ahmadmaaz1.newsy

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import com.ahmadmaaz1.newsy.presentatoin.navgraph.NewsNavGraph
import com.ahmadmaaz1.newsy.ui.theme.NewsyTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    val mainViewModel by viewModels<MainViewModel>()

    @SuppressLint("CoroutineCreationDuringComposition")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window,false)
        installSplashScreen().apply {
            setKeepOnScreenCondition {
                mainViewModel.splashScreen
            }
        }
        enableEdgeToEdge()
        setContent {
            NewsyTheme {
                val isSystemINDateMode = isSystemInDarkTheme()
                val systemController = rememberSystemUiController()
                SideEffect {
                    systemController.setSystemBarsColor(color = Color.Transparent,
                        darkIcons = isSystemINDateMode)
                }
                Box(modifier = Modifier.background(MaterialTheme.colorScheme.background)){
                    NewsNavGraph(mainViewModel.startDestination)
                }
            }
        }
    }
}