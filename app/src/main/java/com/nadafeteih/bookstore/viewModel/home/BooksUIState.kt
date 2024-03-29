package com.nadafeteih.bookstore.viewModel.home

data class BooksUIState(
    val books: List<BookUIState> = emptyList(),
    val isLoading: Boolean = false,
    val error: Int = -1,
)


data class BookUIState(
    val id: String = "",
    val cover: String = "",
    val title: String = "",
    val subTitle: String = "",
    val price: String = "",
    val isSaved: Boolean = false
)

