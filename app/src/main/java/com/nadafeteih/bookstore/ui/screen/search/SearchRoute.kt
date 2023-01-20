package com.nadafeteih.bookstore.ui.screen.search

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable


private const val ROUTE = "Search"
fun NavGraphBuilder.searchRoute(navController: NavHostController) {
    composable(route = ROUTE) {
        SearchScreen(navController)
    }
}