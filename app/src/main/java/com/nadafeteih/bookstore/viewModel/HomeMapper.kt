package com.nadafeteih.bookstore.viewModel

import com.nadafeteih.bookstore.entity.Book


fun List<Book>.toUIState() = map { it.toUIState() }

fun Book.toUIState(): BookUIState {
    return BookUIState(
        id = id,
        cover = cover,
        title = title,
        price = price,
        isSaved = false
    )
}
