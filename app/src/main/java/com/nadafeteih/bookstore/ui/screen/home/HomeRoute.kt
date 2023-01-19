package com.nadafeteih.bookstore.ui.screen.home

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.nadafeteih.bookstore.ui.START_DESTINATION


private const val ROUTE = START_DESTINATION
fun NavGraphBuilder.homeRoute(navController: NavHostController) {
    composable(route = ROUTE) {
        HomeScreen(navController)
    }
}