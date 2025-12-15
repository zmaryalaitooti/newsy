package com.ahmadmaaz1.newsy.presentatoin.navgraph

sealed class Route(val route: String) {
    object OnBoardingScreen: Route("OnBoardingScreen")
    object HomeScreen: Route("HomeScreen")
    object SearchScreen: Route("SearchScreen")
    object Search : Route("Search")
    object BookMarkScreen: Route("BookMarkScreen")
    object DetailScreen: Route("DetailScreen")
    object AppStarNavigation: Route("AppStarNavigation")
    object NewsNavigation: Route("NewsNavigation")
    object NewsNavigationScreen: Route("NewsNavigationScreen")



}