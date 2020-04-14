package com.samit.infosyscodechallenge.data.source

import com.samit.infosyscodechallenge.api.BaseDataSource
import com.samit.infosyscodechallenge.api.InfosysService
import javax.inject.Inject

class FactRemoteDataSource @Inject constructor(private val service: InfosysService) :
    BaseDataSource() {
    suspend fun fetchFacts() =
        getResult {
            service.getFacts()
        }
}