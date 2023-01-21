package com.nadafeteih.bookstore.ui.screen.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.nadafeteih.bookstore.ui.composable.Pager
import com.nadafeteih.bookstore.ui.composable.PagerState
import com.nadafeteih.bookstore.ui.screen.home.compose.BookItem
import com.nadafeteih.bookstore.viewModel.home.BookUIState
import com.nadafeteih.bookstore.viewModel.home.BooksUIState
import com.nadafeteih.bookstore.viewModel.home.HomeViewModel
import java.lang.Math.abs

@Composable
fun HomeScreen(
    navController: NavHostController,
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val state by viewModel.uiState.collectAsState()
    HomeContent(
        state = state,
        onClickBook = {},
        onClickSaved = viewModel::onClickSave
    )
}

@Composable
fun HomeContent(
    state: BooksUIState,
    onClickBook: (BookUIState) -> Unit,
    onClickSaved: (BookUIState) -> Unit
) {

    if (state.books.isNotEmpty()) {
        val pagerState = remember { PagerState(maxPage = state.books.size - 1) }

        Pager(state = pagerState) {
            val book = state.books[commingPage]
            val isSelected = pagerState.currentPage == commingPage
            val filteredOffset = if (abs(pagerState.currentPage - commingPage) < 2) {
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