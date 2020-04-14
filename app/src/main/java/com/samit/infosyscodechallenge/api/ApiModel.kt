package com.samit.infosyscodechallenge.api

import com.google.gson.annotations.SerializedName
import com.samit.infosyscodechallenge.api.ApiKeys.Companion.DESCRIPTION
import com.samit.infosyscodechallenge.api.ApiKeys.Companion.IMAGE
import com.samit.infosyscodechallenge.api.ApiKeys.Companion.TITLE

data class FactApi(
    @SerializedName(TITLE)
    val title: String? = null,
    @SerializedName(DESCRIPTION)
    val description: String? = null,
    @SerializedName(IMAGE)
    val image: String? = null
)

interface ApiKeys {
    companion object {
        const val TITLE = "title"
        const val ROWS = "rows"
        const val DESCRIPTION = "description"
        const val IMAGE = "imageHref"
        const val API_GET_FACTS = "facts.json"
        const val ENDPOINT = "https://dl.dropboxusercontent.com/s/2iodh4vg0eortkl/"
    }
}