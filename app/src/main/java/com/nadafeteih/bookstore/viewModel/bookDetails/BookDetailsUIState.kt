package com.nadafeteih.bookstore.viewModel.bookDetails

import com.nadafeteih.bookstore.entity.Book

data class BookUIState(
    val bookDetail: BookDetailsUIState = BookDetailsUIState(),
    val isSaved: Boolean = false,
    val isLoading: Boolean = false,
    val error: Int = -1,
)

data class BookDetailsUIState(
    val id: String = "",
    val cover: String = "",
    val title: String = "",
    val price: String = "",
    val authors: String = "",
    val description: String = "",
    val language: String = "",
    val pages: String = "",
    val year: String = "",
    val rating: String = "",
    val subtitle: String = "",
    val isSaved: Boolean = false
)

fun Book.toUIState(): BookDetailsUIState {
    return BookDetailsUIState(
        id = id,
        cover = cover,
        title = title,
        price = price,
        authors = authors,
        description = description,
        language = language,
        pages = pages,
        year = year,
        rating = rating,
        subtitle = subtitle,
    )
}

fun BookDetailsUIState.toEntity(): Book {
    return Book(
        id = id,
        cover = cover,
        title = title,
        price = price,
        authors = authors,
        description = description,
        language = language,
        pages = pages,
        year = year,
        rating = rating,
        subtitle = subtitle,
    )
}