package com.nadafeteih.bookstore.viewModel.bookDetails

import androidx.lifecycle.SavedStateHandle

class BookDetailsArgs(savedStateHandle: SavedStateHandle) {

    val bookId: String = checkNotNull(savedStateHandle[BOOK_ID])

    companion object {
        const val BOOK_ID = "book_id"
    }
}