package com.nadafeteih.bookstore.ui.screen.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.nadafeteih.bookstore.ui.composable.Pager
import com.nadafeteih.bookstore.ui.composable.PagerState
import com.nadafeteih.bookstore.ui.screen.bookDetails.navigateToBookDetails
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

    val systemUIController = rememberSystemUiController()
    systemUIController.setStatusBarColor(color = MaterialTheme.colorScheme.background)
    systemUIController.setNavigationBarColor(color = MaterialTheme.colorScheme.secondaryContainer)
    val clickedBook = remember { mutableStateOf(false) }

    HomeContent(
        state = state,
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
    onClickBook: (BookUIState) -> Unit,
    onClickSaved: (BookUIState) -> Unit
) {

    Column {
        Text(modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp, horizontal = 24.dp),text = "Home ...")
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
}