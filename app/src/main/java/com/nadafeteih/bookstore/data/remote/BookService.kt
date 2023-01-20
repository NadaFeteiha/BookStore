package com.nadafeteih.bookstore.data.remote

import com.nadafeteih.bookstore.data.remote.response.BaseResponse
import com.nadafeteih.bookstore.data.remote.response.BookDetailDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface BookService {

    @GET("search/{book_title}")
    suspend fun searchBook(@Path("book_title") bookTitle: String): Response<BaseResponse>

    @GET("new")
    suspend fun getNewBooks(): Response<BaseResponse>

    @GET("books/{isbn}")
    suspend fun getBookDetails(isbn: String):Response<BookDetailDTO>

}