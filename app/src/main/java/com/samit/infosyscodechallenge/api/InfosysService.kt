package com.samit.infosyscodechallenge.api

import retrofit2.Response
import retrofit2.http.GET

interface InfosysService {

    companion object {
        const val ENDPOINT = "https://dl.dropboxusercontent.com/s/2iodh4vg0eortkl/"
    }

    @GET("facts.json")
    suspend fun getFacts(): Response<ResultsResponse<FactApi>>
}