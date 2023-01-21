package com.nadafeteih.bookstore.ui.screen.search

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.nadafeteih.bookstore.ui.composable.BottomBarScreen


fun NavGraphBuilder.searchRoute(navController: NavHostController) {
    composable(route = BottomBarScreen.Search.route) {
        SearchScreen(navController)
    }
}