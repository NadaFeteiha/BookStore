package com.nadafeteih.bookstore.data.remote.response

import kotlinx.serialization.Serializable

@Serializable
data class BaseResponse(
    val books: List<BookDTO>,
    val error: String?,
    val total: String?,
)