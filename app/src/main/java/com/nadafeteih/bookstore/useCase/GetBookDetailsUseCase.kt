package com.nadafeteih.bookstore.useCase

import com.nadafeteih.bookstore.data.repository.BookRepository
import com.nadafeteih.bookstore.entity.Book
import javax.inject.Inject

class GetBookDetailsUseCase @Inject constructor(private val repository: BookRepository) {

    suspend operator fun invoke(bookId: String): Book {
        val savedBook = try {
            repository.getBookById(bookId)
        } catch (t: Throwable) {
            null
        }
        val book = repository.getBookDetails(bookId)

        return if (savedBook != null) {
            book.copy(saved = true)
        } else {
            book
        }

    }

}