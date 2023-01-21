package com.nadafeteih.bookstore.useCase

import com.nadafeteih.bookstore.data.repository.BookRepository
import com.nadafeteih.bookstore.entity.Book
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSavedBooksUseCase @Inject constructor(private val repository: BookRepository) {

    operator fun invoke(): Flow<List<Book>> {
        return repository.getAllBooks()
    }
}