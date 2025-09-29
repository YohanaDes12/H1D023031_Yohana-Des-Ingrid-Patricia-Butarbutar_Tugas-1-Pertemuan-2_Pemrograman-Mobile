package com.unsoed.informatikamobile.data.model

import com.google.gson.annotations.SerializedName

data class BookDoc(
    @SerializedName("title")
    val title: String?,
    @SerializedName("author_name")
    val authorName: List<String>? = null,
    @SerializedName("first_publish_year")
    val firstPublishYear: Int? = null,
    @SerializedName("cover_i")
    val coverId: Int? = null
)