package com.samit.infosyscodechallenge.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import kotlinx.coroutines.Dispatchers
import timber.log.Timber


fun <T, A> resultLiveData(
    databaseQuery: () -> LiveData<T>,
    networkCall: suspend () -> Result<A>,
    saveCallResult: suspend (A) -> Unit
): LiveData<Result<T>> =
    liveData(Dispatchers.IO) {

        emit(Result.loading<T>())
        Timber.d("Infosys Api Called in SIngle Source of Truth")
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