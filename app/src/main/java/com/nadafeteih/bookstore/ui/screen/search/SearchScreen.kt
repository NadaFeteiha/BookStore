package com.nadafeteih.bookstore.ui.screen.search

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController


@Composable
fun SearchScreen(
    navController: NavHostController,
) {
    SearchContent()
}


@Composable
fun SearchContent() {
    Text(text = "Search")
}