package com.nadafeteih.bookstore.data.local

import bookdb.saved.BookEntity
import com.nadafeteih.bookstore.bookdb
import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class BookDataSourceImp @Inject constructor(private val bookDataBase: bookdb) : BookDataSource {

    override suspend fun getBookById(id: String): BookEntity? {
        return withContext(Dispatchers.IO) {
            bookDataBase.bookEntityQueries.getBookById(id).executeAsOneOrNull()
        }
    }

    override fun getAllBooks(): Flow<List<BookEntity>> {
        return bookDataBase.bookEntityQueries.getAllBooks().asFlow().mapToList()

    }

    override suspend fun insertBook(book: BookEntity) {
        return bookDataBase.bookEntityQueries.insertBook(
            id = book.id,
            title = book.title,
            cover = book.cover
        )
    }

    override suspend fun deleteBook(id: String) {
        TODO("Not yet implemented")
    }
}