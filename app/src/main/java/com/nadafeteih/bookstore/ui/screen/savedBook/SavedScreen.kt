package com.nadafeteih.bookstore.ui.screen.savedBook

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.nadafeteih.bookstore.viewModel.home.BookUIState
import com.nadafeteih.bookstore.viewModel.home.BooksUIState
import com.nadafeteih.bookstore.viewModel.savedBook.SavedBookViewModel


@Composable
fun SavedScreen(
    navController: NavHostController,
    viewModel: SavedBookViewModel = hiltViewModel(),
) {
    val state by viewModel.uiState.collectAsState()
    SavedContent(
        state = state,
        onClickSave = viewModel::onClickSave
    )
}


@Composable
fun SavedContent(
    state: BooksUIState,
    onClickSave: (BookUIState) -> Unit
) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(state.books) { book ->
            Text(text = book.title)
        }
    }
}