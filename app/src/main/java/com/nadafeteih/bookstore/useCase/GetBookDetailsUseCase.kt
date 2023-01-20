package com.nadafeteih.bookstore.useCase

import com.nadafeteih.bookstore.data.repository.BookRepository
import com.nadafeteih.bookstore.entity.Book
import javax.inject.Inject

class GetBookDetailsUseCase @Inject constructor(private val repository: BookRepository) {

    suspend operator fun invoke(bookId: String): Book {
        return repository.getBookDetails(bookId)
    }
}