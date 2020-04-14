package com.samit.infosyscodechallenge.api

import com.samit.infosyscodechallenge.api.ApiKeys.Companion.API_GET_FACTS
import retrofit2.Response
import retrofit2.http.GET

interface InfosysService {
    @GET(API_GET_FACTS)
    suspend fun getFacts(): Response<ResultsResponse<FactApi>>
}