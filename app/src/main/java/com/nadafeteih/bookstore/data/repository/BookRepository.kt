package com.nadafeteih.bookstore.data.repository

import com.nadafeteih.bookstore.entity.Book


interface BookRepository {

    suspend fun getNewBooks(): List<Book>

    suspend fun searchBook(bookTitle: String): List<Book>

    suspend fun getBookDetails(bookId: String): Book
}