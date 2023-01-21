package com.nadafeteih.bookstore.ui.screen.savedBook

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.nadafeteih.bookstore.ui.composable.BottomBarScreen


fun NavGraphBuilder.savedRoute(navController: NavHostController) {
    composable(route = BottomBarScreen.Saved.route) {
        SavedScreen(navController)
    }
}