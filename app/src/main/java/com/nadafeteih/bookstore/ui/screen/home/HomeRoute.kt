package com.nadafeteih.bookstore.ui.screen.home

import androidx.compose.runtime.MutableState
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.nadafeteih.bookstore.ui.composable.BottomBarScreen


fun NavGraphBuilder.homeRoute(
    navController: NavHostController,
    isDarkTheme: MutableState<Boolean>
) {
    composable(route = BottomBarScreen.Home.route) {
        HomeScreen(navController,isDarkTheme)
    }
}