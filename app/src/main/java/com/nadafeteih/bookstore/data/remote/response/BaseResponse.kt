package com.nadafeteih.bookstore.data.remote.response

data class BaseResponse(
    val books: List<BookDTO>,
    val error: String?,
    val page: String?,
    val total: String?
)