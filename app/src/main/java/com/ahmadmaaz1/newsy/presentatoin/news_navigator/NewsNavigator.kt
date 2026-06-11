package com.ahmadmaaz1.newsy.presentatoin.news_navigator

import android.media.Image
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.ahmadmaaz1.newsy.R
import com.ahmadmaaz1.newsy.domain.model.Article
import com.ahmadmaaz1.newsy.presentatoin.bookmark.BookMarkScreen
import com.ahmadmaaz1.newsy.presentatoin.bookmark.BookMarkViewmodel
import com.ahmadmaaz1.newsy.presentatoin.detail.DetailScreen
import com.ahmadmaaz1.newsy.presentatoin.detail.DetailViewmodel
import com.ahmadmaaz1.newsy.presentatoin.detail.component.DetailEvent
import com.ahmadmaaz1.newsy.presentatoin.home.BreakingNewsState
import com.ahmadmaaz1.newsy.presentatoin.home.HomeScreen
import com.ahmadmaaz1.newsy.presentatoin.home.HomeViewModel
import com.ahmadmaaz1.newsy.presentatoin.navgraph.Route
import com.ahmadmaaz1.newsy.presentatoin.news.NewsScreen
import com.ahmadmaaz1.newsy.presentatoin.news_navigator.components.NewsBottomNavigation
import com.ahmadmaaz1.newsy.presentatoin.news_navigator.components.NewsNavigate
import com.ahmadmaaz1.newsy.presentatoin.search.NewsSearchViewModel
import com.ahmadmaaz1.newsy.presentatoin.search.SearchScreen

private const val TAG = "NewsNavigator"

data class NewsBottomItems(val image: ImageVector,val text: String)
@Composable
fun NewsNavigator() {

    val isDark = isSystemInDarkTheme()

    val containerColor = if (isDark) Color.Black else Color.White
    val unselectedColor = if (isDark) Color.White else Color.Black
    val selectedColor = Color.Red
    val bottomItems = remember {
        listOf(
            NewsBottomItems(image = Icons.Default.Home, text = "Home"),
            NewsBottomItems(image = Icons.Default.Search, text = "Explore"),
            NewsBottomItems(image = Icons.Default.BookmarkBorder, text = "BookMark"),
        )
    }

    Icons.Default.Home
    val navController = rememberNavController()
    val backStack = navController.currentBackStackEntryAsState().value
    var selectedItem by rememberSaveable { mutableIntStateOf(0) }
    val bottomBarVisibility = remember(backStack) {
        backStack?.destination?.route == Route.HomeScreen.route ||
                backStack?.destination?.route == Route.SearchScreen.route ||
                backStack?.destination?.route == Route.BookMarkScreen.route
    }

    selectedItem = when (backStack?.destination?.route) {
        Route.HomeScreen.route -> 0
        Route.SearchScreen.route -> 1
        Route.BookMarkScreen.route -> 2
        else -> selectedItem
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            if (bottomBarVisibility) {

                NavigationBar(
                    containerColor = containerColor
                ) {

                    bottomItems.forEachIndexed { index, item ->

                        NavigationBarItem(
                            selected = selectedItem == index,
                            onClick = {
                                when (index) {
                                    0 -> navigateToTap(
                                        navController,
                                        Route.HomeScreen.route
                                    )

                                    1 -> navigateToTap(
                                        navController,
                                        Route.SearchScreen.route
                                    )

                                    2 -> navigateToTap(
                                        navController,
                                        Route.BookMarkScreen.route
                                    )
                                }
                            },
                            icon = {
                                Icon(
                                    item.image,
                                    contentDescription = item.text
                                )
                            },
                            label = {
                                Text(item.text)
                            },
                            colors = NavigationBarItemDefaults.colors(
                                selectedIconColor = selectedColor,
                                selectedTextColor = selectedColor,

                                unselectedIconColor = unselectedColor,
                                unselectedTextColor = unselectedColor,

                                // removes the pill/background behind selected item
                                indicatorColor = Color.Transparent
                            )
                        )
                    }
                }
            }
        }
    )    {
        val bottomPadding = it.calculateBottomPadding()

        NavHost(
            navController = navController,
            startDestination = Route.HomeScreen.route,
            modifier = Modifier.padding(bottom = bottomPadding)
        ) {

            composable(route = Route.HomeScreen.route) {
                val viewModel = hiltViewModel<HomeViewModel>()
                val article = viewModel.news.collectAsLazyPagingItems()
                HomeScreen(
                    article = article,
                   viewModel = viewModel,
                    navigatorToDetail = {
                        navigateToDetails(
                            navController = navController,
                            article = it
                        )
                    },
                    navigatorToSeeAll = {list->
                        navController.currentBackStackEntry
                            ?.savedStateHandle
                            ?.set("news_list", list)
                        navigateToTap(
                            navController = navController,
                            route = Route.NewsScreen.route
                        )
                    },
                    navigatorToSearch = {
                        navigateToTap(
                            navController,
                            route = Route.SearchScreen.route
                        )
                    }
                )
            }

            composable(route= Route.NewsScreen.route) {
                val list =
                    navController.previousBackStackEntry
                        ?.savedStateHandle
                        ?.get<List<Article>>("news_list")
                        ?: emptyList()


                NewsScreen(
                    list = list,
                    navigatorToDetail = {
                        navigateToDetails(
                            navController = navController,
                            article = it
                        )
                    },

                    onBackClick = {
                        navController.popBackStack()
                    }
                )
            }

            composable(route = Route.SearchScreen.route) {
                val viewModel = hiltViewModel<NewsSearchViewModel>()
                SearchScreen(
                    viewModel = viewModel,
                    state = viewModel.state.value,
                    event = viewModel::onEvent,
                    navigateToDetails = {
                        navigateToDetails(
                            article = it,
                            navController = navController
                        )
                    },
                    onBackClick = {navController.popBackStack()}
                )
            }

            composable(route = Route.DetailScreen.route) {

                val viewModel = hiltViewModel<DetailViewmodel>()
                if (viewModel.sideEffect != ""){
                    Toast.makeText(LocalContext.current,viewModel.sideEffect, Toast.LENGTH_LONG).show()
                    viewModel.onEven(DetailEvent.RemoveSideEffect)
                }

                navController.previousBackStackEntry?.savedStateHandle?.get<Article?>("article")
                    ?.let { article ->
                        DetailScreen(
                            viewmodel = viewModel,
                            article = article,
                            event = viewModel::onEven,
                            navigateUp = { navController.navigateUp() })
                    }
            }

            composable(route = Route.BookMarkScreen.route) {
                val viewmodel = hiltViewModel<BookMarkViewmodel>()
                BookMarkScreen(
                    state = viewmodel.state.value,
                    navigateToDetails = {
                        navigateToDetails(
                            article = it,
                            navController = navController
                        )
                    }
                )
            }
        }

    }
}



private fun navigateToTap(navController: NavController, route: String) {
    navController.navigate(route) {
        navController.graph.startDestinationRoute?.let { homeScreen ->
            popUpTo(homeScreen) {
                saveState = true
            }
            restoreState = true
            launchSingleTop = true
        }

    }
}

private fun navigateToDetails(article: Article, navController: NavController) {
    navController.currentBackStackEntry?.savedStateHandle?.set("article", article)
    navController.navigate(route = Route.DetailScreen.route)
}

