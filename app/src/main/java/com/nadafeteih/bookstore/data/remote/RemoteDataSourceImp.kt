package com.nadafeteih.bookstore.data.remote

import com.nadafeteih.bookstore.data.remote.response.BookDTO
import com.nadafeteih.bookstore.data.remote.response.BookDetailDTO
import com.nadafeteih.bookstore.entity.Book
import retrofit2.Response
import javax.inject.Inject

class RemoteDataSourceImp @Inject constructor(private val bookService: BookService) :
    RemoteDataSource {

    override suspend fun getNewBooks(): List<BookDTO> {
        return wrap { bookService.getNewBooks() }.books
    }

    override suspend fun searchBook(bookTitle: String): List<BookDTO> {
        return wrap { bookService.searchBook(bookTitle) }.books
    }

    override suspend fun getBookDetails(bookId: String): BookDetailDTO {
        return wrap { bookService.getBookDetails(bookId) }
    }

    private suspend fun <T> wrap(function: suspend () -> Response<T>): T {
        val response = function()
        return if (response.isSuccessful && response.body() != null) {
            response.body() as T
        } else {
            throw Throwable("Network Error")
        }
    }
}