package com.nadafeteih.bookstore.useCase

import com.nadafeteih.bookstore.data.repository.BookRepository
import com.nadafeteih.bookstore.entity.Book
import javax.inject.Inject

class SaveBookUseCase @Inject constructor(private val repository: BookRepository) {
    suspend operator fun invoke(book: Book, isSaved: Boolean) {
        if (isSaved) {
            repository.deleteBook(book.id)
        } else {
            repository.insertBook(book)
        }
    }
}