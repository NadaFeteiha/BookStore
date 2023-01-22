package com.nadafeteih.bookstore.data.repository.mapper

import bookdb.saved.BookEntity
import com.nadafeteih.bookstore.entity.Book

fun List<BookEntity>.toEntity() = map { it.toEntity() }

fun BookEntity.toEntity(): Book {
    return Book(
        id = id,
        title = title,
        cover = cover,
        subtitle = subtitle,
        price = price
    )
}

fun Book.toEntity(): BookEntity {
    return BookEntity(
        id = id,
        title = title,
        cover = cover,
        price = price,
        subtitle = subtitle,
    )
}