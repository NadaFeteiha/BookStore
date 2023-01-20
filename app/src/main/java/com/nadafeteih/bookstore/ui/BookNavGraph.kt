package com.nadafeteih.bookstore.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.nadafeteih.bookstore.ui.composable.BottomBarScreen
import com.nadafeteih.bookstore.ui.screen.home.homeRoute
import com.nadafeteih.bookstore.ui.screen.saved.savedRoute
import com.nadafeteih.bookstore.ui.screen.search.searchRoute


@Composable
fun BookNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = BottomBarScreen.Home.route
    ) {
        homeRoute(navController = navController)
        savedRoute(navController = navController)
        searchRoute(navController = navController)
    }
}