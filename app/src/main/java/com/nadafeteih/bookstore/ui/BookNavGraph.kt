package com.nadafeteih.bookstore.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.nadafeteih.bookstore.ui.screen.home.homeRoute


const val START_DESTINATION = "HOME"

@Composable
fun BookNavGraph(
    navController: NavHostController, startDestination: String,
) {
    NavHost(navController = navController, startDestination = startDestination) {
        homeRoute(navController)
    }
}