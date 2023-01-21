package com.nadafeteih.bookstore.data.repository

import com.nadafeteih.bookstore.data.local.BookDataSource
import com.nadafeteih.bookstore.data.remote.RemoteDataSource
import com.nadafeteih.bookstore.data.repository.mapper.toEntity
import com.nadafeteih.bookstore.entity.Book
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class BookRepositoryImp @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val dataSource: BookDataSource
) : BookRepository {

    override suspend fun getNewBooks(): List<Book> {
        return remoteDataSource.getNewBooks().toEntity()
    }

    override suspend fun searchBook(bookTitle: String): List<Book> {
        return remoteDataSource.searchBook(bookTitle).toEntity()
    }

    override suspend fun getBookDetails(bookId: String): Book {
        return remoteDataSource.getBookDetails(bookId).toEntity()
    }

    override suspend fun getBookById(id: String): Book {
        return dataSource.getBookById(id)?.toEntity() ?: throw Throwable("NOT Found")
    }

    override fun getAllBooks(): Flow<List<Book>> {
        return dataSource.getAllBooks().map { it.toEntity() }
    }

    override suspend fun insertBook(book: Book) {
        return dataSource.insertBook(book = book.toEntity())
    }

    override suspend fun deleteBook(id: String) {
       dataSource.deleteBook(id)
    }
}