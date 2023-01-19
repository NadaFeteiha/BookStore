package com.nadafeteih.bookstore.data.remote

import com.nadafeteih.bookstore.data.remote.response.BookDTO
import retrofit2.Response
import javax.inject.Inject

class RemoteDataSourceImp @Inject constructor(private val bookService: BookService) : RemoteDataSource {

    override suspend fun getNewBooks(): List<BookDTO> {
        return wrap { bookService.getNewBooks() }.books
    }

    override suspend fun searchBook(bookTitle: String): List<BookDTO> {
        return wrap { bookService.getNewBooks() }.books
    }

    override suspend fun getBookDetails(bookId: Int) {
        TODO("Not yet implemented")
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