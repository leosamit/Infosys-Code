package com.samit.infosyscodechallenge.api

import com.google.gson.annotations.SerializedName

data class ResultsResponse<T>(
    @SerializedName(ApiKeys.TITLE)
    val title: String? = null,
    @SerializedName(ApiKeys.ROWS)
    val results: List<T>
)