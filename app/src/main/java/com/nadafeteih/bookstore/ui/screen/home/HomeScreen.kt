package com.nadafeteih.bookstore.ui.screen.home

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.nadafeteih.bookstore.R
import com.nadafeteih.bookstore.ui.composable.AppBar
import com.nadafeteih.bookstore.ui.composable.Pager
import com.nadafeteih.bookstore.ui.composable.PagerState
import com.nadafeteih.bookstore.ui.screen.bookDetails.navigateToBookDetails
import com.nadafeteih.bookstore.ui.screen.home.compose.BookItem
import com.nadafeteih.bookstore.viewModel.home.BookUIState
import com.nadafeteih.bookstore.viewModel.home.BooksUIState
import com.nadafeteih.bookstore.viewModel.home.HomeViewModel

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

@OptIn(ExperimentalPagerApi::class)
@Composable
fun HomeContent(
    modifier: Modifier = Modifier,
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
            HorizontalPager(
                count = state.books.size,
                contentPadding = PaddingValues(16.dp)
            ) { page ->
                val pagerState = remember { PagerState(maxPage = state.books.size - 1) }
                Pager(
                    state = pagerState,
                    modifier = modifier,
                ) {
                    val book = state.books[comingPage]
                    val isSelected = pagerState.currentPage == comingPage
                    val filteredOffset =
                        if (kotlin.math.abs(pagerState.currentPage - comingPage) < 2) {
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
}

