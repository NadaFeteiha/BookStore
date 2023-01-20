package com.nadafeteih.bookstore.ui.screen.saved

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.nadafeteih.bookstore.viewModel.saved.SavedBookViewModel


@Composable
fun SavedScreen(
    navController: NavHostController,
    viewModel: SavedBookViewModel = hiltViewModel(),
) {
    val state by viewModel.uiState.collectAsState()
    SavedContent()
}


@Composable
fun SavedContent() {
    Text(text = "saved")
}