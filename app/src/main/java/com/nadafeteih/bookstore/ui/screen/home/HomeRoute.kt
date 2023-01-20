package com.nadafeteih.bookstore.ui.screen.home

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable


private const val ROUTE = "Home"
fun NavGraphBuilder.homeRoute(navController: NavHostController) {
    composable(route = ROUTE) {
        HomeScreen(navController)
    }
}