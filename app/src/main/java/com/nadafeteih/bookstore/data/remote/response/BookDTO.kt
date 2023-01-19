package com.nadafeteih.bookstore.data.remote.response

data class BookDTO(
    val image: String?,
    val isbn13: String?,
    val price: String?,
    val subtitle: String?,
    val title: String?,
    val url: String?
)