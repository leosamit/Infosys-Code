package com.samit.infosyscodechallenge.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import com.samit.infosyscodechallenge.data.mapper.toFactUI
import kotlinx.coroutines.Dispatchers
import timber.log.Timber

//Database acts as single source of truth. Data is always fetched from database after api call
fun <T, A> resultLiveData(
    databaseQuery: () -> LiveData<T>,
    networkCall: suspend () -> Result<A>,
    saveCallResult: suspend (A) -> Unit
): LiveData<Result<T>> =
    liveData(Dispatchers.IO) {
        Timber.d("Single SOurce Called")
        emit(Result.loading<T>())
        val source = databaseQuery.invoke().map { Result.success(it) }
        emitSource(source)

        val responseStatus = networkCall.invoke()
        if (responseStatus.status == Result.Status.SUCCESS) {
            saveCallResult(responseStatus.data!!)
        } else if (responseStatus.status == Result.Status.ERROR) {
            emit(Result.error<T>(responseStatus.message!!))
            emitSource(source)
        }
    }