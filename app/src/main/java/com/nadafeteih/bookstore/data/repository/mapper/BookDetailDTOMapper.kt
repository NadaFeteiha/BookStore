package com.nadafeteih.bookstore.data.repository.mapper

import com.nadafeteih.bookstore.data.remote.response.BookDetailDTO
import com.nadafeteih.bookstore.entity.Book


internal fun BookDetailDTO.toEntity(): Book {
    return Book(
        id = isbn13 ?: "",
        cover = image ?: "",
        price = price ?: "",
        title = title ?: "",
        authors = authors ?: "",
        description = desc ?: "",
        language = language ?: "",
        pages = pages ?: "",
        year = year ?: "",
        rating = rating ?: "",
        subtitle = subtitle ?: "",
    )
}