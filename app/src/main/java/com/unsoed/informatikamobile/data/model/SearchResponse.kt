package com.unsoed.informatikamobile.data.model

import com.google.gson.annotations.SerializedName

data class SearchResponse(
    @SerializedName("docs")
    val docs: List<BookDoc>,
    @SerializedName("numFound")
    val numFound: Int = 0,
    @SerializedName("start")
    val start: Int = 0
)