package com.nadafeteih.bookstore.ui.screen.home

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.nadafeteih.bookstore.ui.composable.Pager
import com.nadafeteih.bookstore.ui.composable.PagerState
import com.nadafeteih.bookstore.ui.screen.home.compose.BookItem
import com.nadafeteih.bookstore.viewModel.HomeUIState
import com.nadafeteih.bookstore.viewModel.HomeViewModel
import java.lang.Math.abs

@Composable
fun HomeScreen(
    navController: NavHostController,
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val state by viewModel.uiState.collectAsState()
    HomeContent(
        state = state
    )
}


@Composable
fun HomeContent(
    state: HomeUIState
) {

    if (state.books.isNotEmpty()) {
        val pagerState = remember { PagerState(maxPage = state.books.size - 1) }

        Pager(state = pagerState, modifier = Modifier.height(645.dp)) {
            val movie = state.books[commingPage]
//            imageId.value = imageIds[pagerState.currentPage]
            val isSelected = pagerState.currentPage == commingPage

            // Only one page before and one page after the selected page needs to receive non zero offset
            val filteredOffset = if (abs(pagerState.currentPage - commingPage) < 2) {
                currentPageOffset
            } else 0f

            BookItem(
                movie,
                isSelected,
                filteredOffset,
            )
        }
    }

}