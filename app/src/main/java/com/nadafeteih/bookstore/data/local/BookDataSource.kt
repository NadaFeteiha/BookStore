package com.nadafeteih.bookstore.data.local

import bookdb.saved.BookEntity
import kotlinx.coroutines.flow.Flow

interface BookDataSource {

    suspend fun getBookById(id: String): BookEntity?

    fun getAllBooks(): Flow<List<BookEntity>>

    suspend fun insertBook(book: BookEntity)

    suspend fun deleteBook(id: String)


}