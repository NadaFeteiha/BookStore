package com.nadafeteih.bookstore.entity

data class Book(
    val id: String,
    val cover: String = "",
    val price: String = "",
    val title: String = "",
    val authors: String = "",
    val description: String = "",
    val language: String = "",
    val pages: String = "",
    val year: String = "",
    val rating: String = "",
    val subtitle: String = "",
)