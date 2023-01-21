package com.nadafeteih.bookstore.data.repository

import com.nadafeteih.bookstore.entity.Book
import kotlinx.coroutines.flow.Flow


interface BookRepository {

    suspend fun getNewBooks(): List<Book>

    suspend fun searchBook(bookTitle: String): List<Book>

    suspend fun getBookDetails(bookId: String): Book

    suspend fun getBookById(id: String): Book

    fun getAllBooks(): Flow<List<Book>>

    suspend fun insertBook(book: Book)

    suspend fun deleteBook(id: String)
}