package com.nadafeteih.bookstore.ui.screen.search

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.google.accompanist.systemuicontroller.rememberSystemUiController


@Composable
fun SearchScreen(
    navController: NavHostController,
) {
    val systemUIController = rememberSystemUiController()
    systemUIController.setStatusBarColor(color = MaterialTheme.colorScheme.background)

    SearchContent()
}


@Composable
fun SearchContent() {
    Text(text = "Search")
}