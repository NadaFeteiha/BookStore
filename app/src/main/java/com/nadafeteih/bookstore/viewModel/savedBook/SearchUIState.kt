package com.nadafeteih.bookstore.viewModel.savedBook

import com.nadafeteih.bookstore.viewModel.home.BookUIState


data class SearchUIState(
    val books: List<BookUIState> = emptyList(),
    val isLoading: Boolean = false,
    val error: Int = -1,
    val query: String = ""
)