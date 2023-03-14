package com.nadafeteih.bookstore.data.remote

import com.nadafeteih.bookstore.BuildConfig
import com.nadafeteih.bookstore.data.remote.response.BaseResponse
import com.nadafeteih.bookstore.data.remote.response.BookDTO
import com.nadafeteih.bookstore.data.remote.response.BookDetailDTO
import com.nadafeteih.bookstore.data.remote.response.SearchResponse
import io.ktor.client.features.*
import javax.inject.Inject
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.url

class RemoteDataSourceImp @Inject constructor(private val client: HttpClient) :
    RemoteDataSource {

    override suspend fun getNewBooks(): List<BookDTO> {
        return tryToCall<BaseResponse> { client.get { url(BuildConfig.BASE_URL + "new") } }.books

    }

    override suspend fun searchBook(bookTitle: String): List<BookDTO> {
        return tryToCall<SearchResponse> { client.get { url(BuildConfig.BASE_URL + "search/$bookTitle") } }.books
    }

    override suspend fun getBookDetails(bookId: String): BookDetailDTO {
        return tryToCall { client.get { url(BuildConfig.BASE_URL + "books/$bookId") } }
    }

    suspend fun <T> tryToCall(call: suspend () -> T): T {
        return try {
            call()
        } catch (e: RedirectResponseException) {
            throw Throwable("")
        } catch (e: ClientRequestException) {
            throw Throwable("")
        } catch (e: ServerResponseException) {
            throw Throwable("")
        } catch (e: Exception) {
            throw Throwable("")
        }
    }
}