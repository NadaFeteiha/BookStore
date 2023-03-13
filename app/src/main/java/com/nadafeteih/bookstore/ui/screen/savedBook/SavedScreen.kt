package com.nadafeteih.bookstore.ui.screen.savedBook

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.nadafeteih.bookstore.R
import com.nadafeteih.bookstore.ui.screen.bookDetails.navigateToBookDetails
import com.nadafeteih.bookstore.ui.composable.AppBar
import com.nadafeteih.bookstore.ui.screen.savedBook.compose.SavedBookItem
import com.nadafeteih.bookstore.viewModel.home.BookUIState
import com.nadafeteih.bookstore.viewModel.home.BooksUIState
import com.nadafeteih.bookstore.viewModel.savedBook.SavedBookViewModel


@Composable
fun SavedScreen(
    navController: NavHostController,
    viewModel: SavedBookViewModel = hiltViewModel(),
) {
    val state by viewModel.uiState.collectAsState()
    val clickedBook = remember { mutableStateOf(false) }
    val systemUIController = rememberSystemUiController()
    systemUIController.setStatusBarColor(color = MaterialTheme.colorScheme.background)

    SavedContent(
        state = state,
        onClickSave = viewModel::onClickSave,
        onClickBookDetails = {
            if (!clickedBook.value) {
                navController.navigateToBookDetails(it.id)
            }
            clickedBook.value = !clickedBook.value
        }
    )
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SavedContent(
    state: BooksUIState,
    onClickSave: (BookUIState) -> Unit,
    onClickBookDetails: (BookUIState) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        AppBar(title = R.string.saved_book, isHome = false)

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            items(state.books, key = { it.id }) { book ->
                SavedBookItem(
                    modifier = Modifier.animateItemPlacement(),
                    book = book,
                    onclickUnSave = onClickSave,
                    onClickBookDetails = onClickBookDetails
                )
            }
        }
    }
}