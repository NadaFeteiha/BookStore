package com.nadafeteih.bookstore.ui.screen.search

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.nadafeteih.bookstore.ui.screen.bookDetails.navigateToBookDetails
import com.nadafeteih.bookstore.ui.screen.savedBook.compose.SavedBookItem
import com.nadafeteih.bookstore.viewModel.home.BookUIState
import com.nadafeteih.bookstore.viewModel.savedBook.SearchUIState
import com.nadafeteih.bookstore.viewModel.searchBook.SearchViewModel
import com.nadafeteih.bookstore.ui.screen.search.compose.SearchBar

@Composable
fun SearchScreen(
    navController: NavHostController,
    viewModel: SearchViewModel = hiltViewModel(),
) {
    val systemUIController = rememberSystemUiController()
    systemUIController.setStatusBarColor(color = MaterialTheme.colorScheme.background)
    val state by viewModel.uiState.collectAsState()
    val clickedBook = remember { mutableStateOf(false) }

    SearchContent(
        state = state,
        onQueryChange = viewModel::onQueryChange,
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
fun SearchContent(
    state: SearchUIState,
    onQueryChange: (String) -> Unit,
    onClickBookDetails: (BookUIState) -> Unit
) {

    LazyColumn(modifier = Modifier.fillMaxSize()) {
        stickyHeader {
            SearchBar(
                query = state.query,
                onQueryChange = onQueryChange,
                searching = state.isLoading,
            )
        }

        items(state.books, key = { it.id }) { book ->
            SavedBookItem(
                modifier = Modifier.animateItemPlacement(),
                book = book,
                onclickUnSave = {},
                onClickBookDetails = onClickBookDetails,
                isSaveIconVisible = false
            )
        }
    }


}


