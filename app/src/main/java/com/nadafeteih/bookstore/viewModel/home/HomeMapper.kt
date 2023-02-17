package com.nadafeteih.bookstore.viewModel.home

import com.nadafeteih.bookstore.entity.Book


fun List<Book>.toUIState() = map { it.toUIState() }

fun Book.toUIState(): BookUIState {
    return BookUIState(
        id = id,
        cover = cover,
        title = title,
        subTitle = subtitle,
        price = price,
        isSaved = saved
    )
}


fun BookUIState.toEntity(): Book {
    return Book(
        id = id,
        cover = cover,
        title = title,
        subtitle = subTitle,
        price = price,
    )
}