package com.nadafeteih.bookstore.data.remote.response


import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class BookDetailDTO(
    @SerializedName("authors")
    val authors: String?,
    @SerializedName("desc")
    val desc: String?,
    @SerializedName("error")
    val error: String?,
    @SerializedName("image")
    val image: String?,
    @SerializedName("isbn10")
    val isbn10: String?,
    @SerializedName("isbn13")
    val isbn13: String?,
    @SerializedName("language")
    val language: String?,
    @SerializedName("pages")
    val pages: String?,
    @SerializedName("price")
    val price: String?,
    @SerializedName("publisher")
    val publisher: String?,
    @SerializedName("rating")
    val rating: String?,
    @SerializedName("subtitle")
    val subtitle: String?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("url")
    val url: String?,
    @SerializedName("year")
    val year: String?
)