package com.ahmadmaaz1.newsy.presentatoin.navgraph


import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.ahmadmaaz1.newsy.presentatoin.news_navigator.NewsNavigator
import com.ahmadmaaz1.newsy.presentatoin.onboarding.ONBoardingScreen
import com.ahmadmaaz1.newsy.presentatoin.onboarding.OnBoardingViewModel

@Composable
fun NewsNavGraph(startDestination: String) {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = startDestination) {

        navigation(
            route = Route.AppStarNavigation.route,
            startDestination = Route.OnBoardingScreen.route
        ) {
            composable(route = Route.OnBoardingScreen.route) {
                val viewModel: OnBoardingViewModel = hiltViewModel()
                ONBoardingScreen(viewModel::onEvent)
            }
        }

        navigation(
            route = Route.NewsNavigation.route,
            startDestination = Route.NewsNavigationScreen.route
        ) {
            composable(route = Route.NewsNavigationScreen.route) {

                NewsNavigator()
            }
        }


    }

}