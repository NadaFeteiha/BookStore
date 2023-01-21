package com.nadafeteih.bookstore.data.repository.mapper

import com.nadafeteih.bookstore.data.remote.response.BookDTO
import com.nadafeteih.bookstore.entity.Book

internal fun List<BookDTO>.toEntity(): List<Book> = map { it.toEntity() }

internal fun BookDTO.toEntity(): Book {
    return Book(
        id = isbn13 ?: "",
        cover = image ?: "",
        price = price ?: "",
        title = title ?: "",
        subtitle = subtitle ?: ""
    )
}