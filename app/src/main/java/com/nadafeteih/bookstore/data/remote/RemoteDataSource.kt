package com.nadafeteih.bookstore.data.remote

import com.nadafeteih.bookstore.data.remote.response.BookDTO
import com.nadafeteih.bookstore.data.remote.response.BookDetailDTO

interface RemoteDataSource {

    suspend fun getNewBooks(): List<BookDTO>

    suspend fun searchBook(bookTitle: String): List<BookDTO>

    suspend fun getBookDetails(bookId: String): BookDetailDTO

}