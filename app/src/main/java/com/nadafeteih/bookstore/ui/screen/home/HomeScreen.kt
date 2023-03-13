package com.nadafeteih.bookstore.ui.screen.home

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.nadafeteih.bookstore.R
import com.nadafeteih.bookstore.ui.composable.Pager
import com.nadafeteih.bookstore.ui.composable.PagerState
import com.nadafeteih.bookstore.ui.screen.bookDetails.navigateToBookDetails
import com.nadafeteih.bookstore.ui.screen.home.compose.BookItem
import com.nadafeteih.bookstore.ui.composable.AppBar
import com.nadafeteih.bookstore.viewModel.home.BookUIState
import com.nadafeteih.bookstore.viewModel.home.BooksUIState
import com.nadafeteih.bookstore.viewModel.home.HomeViewModel
import androidx.compose.runtime.*

@Composable
fun HomeScreen(
    navController: NavHostController,
    appTheme: MutableState<Boolean>,
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val state by viewModel.uiState.collectAsState()
    val clickedBook = remember { mutableStateOf(false) }

    HomeContent(
        state = state,
        appTheme = appTheme,
        onClickBook = {
            if (!clickedBook.value) {
                navController.navigateToBookDetails(it.id)
            }
            clickedBook.value = !clickedBook.value
        },
        onClickSaved = viewModel::onClickSave
    )
}

@Composable
fun HomeContent(
    state: BooksUIState,
    appTheme: MutableState<Boolean>,
    onClickBook: (BookUIState) -> Unit,
    onClickSaved: (BookUIState) -> Unit
) {

    Column {
        AppBar(
            appTheme = appTheme,
            title = R.string.home
        )
        if (state.books.isNotEmpty()) {
            val pagerState = remember { PagerState(maxPage = state.books.size - 1) }

            Pager(state = pagerState) {
                val book = state.books[commingPage]
                val isSelected = pagerState.currentPage == commingPage
                val filteredOffset =
                    if (kotlin.math.abs(pagerState.currentPage - commingPage) < 2) {
                        currentPageOffset
                    } else 0f

                BookItem(
                    state = book, isSelected,
                    filteredOffset,
                    onClickBook = onClickBook,
                    onClickSaved = onClickSaved
                )
            }
        }
    }
}