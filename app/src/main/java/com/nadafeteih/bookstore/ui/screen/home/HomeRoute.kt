package com.nadafeteih.bookstore.ui.screen.home

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.nadafeteih.bookstore.ui.composable.BottomBarScreen


fun NavGraphBuilder.homeRoute(navController: NavHostController) {
    composable(route = BottomBarScreen.Home.route) {
        HomeScreen(navController)
    }
}