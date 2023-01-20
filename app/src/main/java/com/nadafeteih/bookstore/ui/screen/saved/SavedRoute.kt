package com.nadafeteih.bookstore.ui.screen.saved

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable


private const val ROUTE = "Saved"
fun NavGraphBuilder.savedRoute(navController: NavHostController) {
    composable(route = ROUTE) {
        SavedScreen(navController)
    }
}