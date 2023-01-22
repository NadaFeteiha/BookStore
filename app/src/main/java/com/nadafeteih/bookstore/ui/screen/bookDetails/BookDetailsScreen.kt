package com.nadafeteih.bookstore.ui.screen.bookDetails

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.nadafeteih.bookstore.viewModel.bookDetails.BookDetailsUIState
import com.nadafeteih.bookstore.viewModel.bookDetails.BookDetailsViewModel
import com.nadafeteih.bookstore.viewModel.bookDetails.BookUIState


@Composable
fun BookDetailsScreen(
    navController: NavHostController,
    viewModel: BookDetailsViewModel = hiltViewModel(),
) {
    val state by viewModel.uiState.collectAsState()
    BookDetailsContent(
        state = state,
        onClickSaved = viewModel::onClickSave
    )
}


@Composable
fun BookDetailsContent(
    state: BookUIState,
    onClickSaved: (BookDetailsUIState) -> Unit
) {

    Text(text = state.bookDetail.title)
}