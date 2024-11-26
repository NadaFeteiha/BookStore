package com.nadafeteih.bookstore.data.remote

import com.nadafeteih.bookstore.BuildConfig
import com.nadafeteih.bookstore.data.remote.response.BaseResponse
import com.nadafeteih.bookstore.data.remote.response.BookDTO
import com.nadafeteih.bookstore.data.remote.response.BookDetailDTO
import com.nadafeteih.bookstore.data.remote.response.SearchResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.url
import io.ktor.client.statement.HttpResponse
import javax.inject.Inject

class RemoteDataSourceImp @Inject constructor(private val client: HttpClient) :
    RemoteDataSource {

    override suspend fun getNewBooks(): List<BookDTO> {
        return tryToExecute<BaseResponse> { client.get { url(BuildConfig.BASE_URL + "new") } }.books

    }

    override suspend fun searchBook(bookTitle: String): List<BookDTO> {
        return tryToExecute<SearchResponse> { client.get { url(BuildConfig.BASE_URL + "search/$bookTitle") } }.books
    }

    override suspend fun getBookDetails(bookId: String): BookDetailDTO {
        return tryToExecute { client.get { url(BuildConfig.BASE_URL + "books/$bookId") } }
    }


    private suspend inline fun <reified T> tryToExecute(method: HttpClient.() -> HttpResponse): T {
        try {
            val response = client.method()
            return response.body<T>()
        } catch (e: Throwable) {
            throw e
        }
    }
}