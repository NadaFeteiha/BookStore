package com.nadafeteih.bookstore.ui.screen.savedBook.compose

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.nadafeteih.bookstore.viewModel.home.BookUIState
import com.nadafeteih.bookstore.viewModel.home.BooksUIState

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SavedBooks(
    modifier: Modifier = Modifier,
    state: BooksUIState,
    onClickSave: (BookUIState) -> Unit,
    onClickBookDetails: (BookUIState) -> Unit
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
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