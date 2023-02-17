package com.nadafeteih.bookstore.useCase

import com.nadafeteih.bookstore.data.repository.BookRepository
import com.nadafeteih.bookstore.entity.Book
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetNewBooksUseCase @Inject constructor(private val repository: BookRepository) {

    private val savedBook: MutableList<Book> = mutableListOf()

    suspend operator fun invoke(): List<Book> {
        val books = repository.getNewBooks()
        val savedBooks = savedBook.map { it.id }
        return books.map {
            if (it.id in savedBooks) {
                it.copy(saved = true)
            } else {
                it
            }
        }

    }

    suspend fun getSavedBook() {
        repository.getAllBooks().collect {
            savedBook.addAll(it)
        }
    }
}