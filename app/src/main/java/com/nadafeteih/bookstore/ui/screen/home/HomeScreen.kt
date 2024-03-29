package com.nadafeteih.bookstore.ui.screen.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.nadafeteih.bookstore.R
import com.nadafeteih.bookstore.ui.composable.AppBar
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
        state = state, appTheme = appTheme, onClickBook = {
            if (!clickedBook.value) {
                navController.navigateToBookDetails(it.id)
            }
            clickedBook.value = !clickedBook.value
        }, onClickSaved = viewModel::onClickSave
    )
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeContent(
    modifier: Modifier = Modifier,
    state: BooksUIState,
    appTheme: MutableState<Boolean>,
    onClickBook: (BookUIState) -> Unit,
    onClickSaved: (BookUIState) -> Unit
) {
    val pagerState = rememberPagerState()

    Column {
        AppBar(
            appTheme = appTheme, title = R.string.home
        )
        if (state.books.isNotEmpty()) {

            HorizontalPager(
                pageCount = state.books.size,
                contentPadding = PaddingValues(16.dp),
                state = pagerState,
                beyondBoundsPageCount = 2,
            ) { page ->

                BookItem(
                    state = state.books[page],
                    page = page,
                    pagerState = pagerState,
                    onClickBook = onClickBook,
                    onClickSaved = onClickSaved
                )
            }
        }
    }
}

