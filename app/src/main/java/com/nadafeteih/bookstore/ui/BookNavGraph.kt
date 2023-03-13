package com.nadafeteih.bookstore.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.nadafeteih.bookstore.ui.composable.BottomBarScreen
import com.nadafeteih.bookstore.ui.screen.bookDetails.bookDetailsRoute
import com.nadafeteih.bookstore.ui.screen.home.homeRoute
import com.nadafeteih.bookstore.ui.screen.savedBook.savedRoute
import com.nadafeteih.bookstore.ui.screen.search.searchRoute


@Composable
fun BookNavGraph(navController: NavHostController, isDarkTheme: MutableState<Boolean>) {
    NavHost(
        navController = navController,
        startDestination = BottomBarScreen.Home.route
    ) {
        homeRoute(navController = navController, isDarkTheme)
        savedRoute(navController = navController)
        searchRoute(navController = navController)
        bookDetailsRoute(navController = navController)
    }
}