package com.nadafeteih.bookstore.data.remote.response

import kotlinx.serialization.Serializable

@Serializable
data class SearchResponse(
    val error: String?,
    val total: String?,
    val page: String?,
    val books: List<BookDTO>,
)
