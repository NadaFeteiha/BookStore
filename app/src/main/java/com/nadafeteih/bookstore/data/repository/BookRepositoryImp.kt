package com.nadafeteih.bookstore.data.repository

import com.nadafeteih.bookstore.data.remote.BookService
import com.nadafeteih.bookstore.data.remote.RemoteDataSource
import com.nadafeteih.bookstore.data.remote.RemoteDataSourceImp
import com.nadafeteih.bookstore.data.remote.response.BaseResponse
import com.nadafeteih.bookstore.data.remote.response.BookDTO
import com.nadafeteih.bookstore.data.repository.mapper.toEntity
import com.nadafeteih.bookstore.entity.Book
import javax.inject.Inject

class BookRepositoryImp @Inject constructor(private val remoteDataSource: RemoteDataSource) :
    BookRepository {

    override suspend fun getNewBooks(): List<Book> {
        return remoteDataSource.getNewBooks().toEntity()
    }

    override suspend fun searchBook(bookTitle: String): List<Book> {
        return remoteDataSource.searchBook(bookTitle).toEntity()
    }

    override suspend fun getBookDetails(bookId: String):Book {
        return remoteDataSource.getBookDetails(bookId).toEntity()
    }
}