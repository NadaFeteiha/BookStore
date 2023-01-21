package com.nadafeteih.bookstore.ui.screen.bookDetails

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable


private const val ROUTE = "BookDetailsRoute"

fun NavGraphBuilder.bookDetailsRoute(navController: NavHostController) {
    composable(route = ROUTE) {
        BookDetailsScreen(navController)
    }
}