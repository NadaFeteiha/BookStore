package com.nadafeteih.bookstore.useCase

import com.nadafeteih.bookstore.data.repository.BookRepository
import com.nadafeteih.bookstore.entity.Book
import kotlinx.coroutines.delay
import javax.inject.Inject

class SearchBookUseCase @Inject constructor(private val repository: BookRepository) {

    suspend operator fun invoke(bookTitle: String): List<Book> {
        return if (bookTitle.isNotEmpty()) {
            repository.searchBook(bookTitle)
        } else {
            throw Throwable("Empty")
        }
    }
}