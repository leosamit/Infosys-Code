package com.samit.infosyscodechallenge.data.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.samit.infosyscodechallenge.data.Result
import com.samit.infosyscodechallenge.data.db.FactDao
import com.samit.infosyscodechallenge.data.mapper.toFactDB
import com.samit.infosyscodechallenge.data.mapper.toFactUI
import com.samit.infosyscodechallenge.data.source.FactRemoteDataSource
import com.samit.infosyscodechallenge.ui.model.FactUI
import com.samit.infosyscodechallenge.util.CoroutineDispatcherProvider
import com.samit.infosyscodechallenge.util.postError
import com.samit.infosyscodechallenge.util.postLoading
import com.samit.infosyscodechallenge.util.postSuccess
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class FactsRepository @Inject constructor(
    private val dao: FactDao,
    private val remoteSource: FactRemoteDataSource,
    dispatcherProvider: CoroutineDispatcherProvider
) {
    private val _factsLivedata: MutableLiveData<Result<List<FactUI>>> =
        MutableLiveData()
    private val parentJob = Job()
    private val scope = CoroutineScope(dispatcherProvider.mainDispatcher + parentJob)
    private val _titleResponse = MutableLiveData<String>()
    val titleResponse: LiveData<String>
        get() = _titleResponse


    //Load from network and save in database
    fun getFactsNetworkPersist() {
        _factsLivedata.postLoading()
        scope.launch {
            val responseStatus = remoteSource.fetchFacts()
            when (responseStatus.status) {
                Result.Status.SUCCESS -> {
                    _titleResponse.postValue(responseStatus.data!!.title.toString())
                    dao.insertAllFacts(responseStatus.data.results.map {
                        toFactDB(it)
                    }.filter { !it.title.isNullOrEmpty() })
                    _factsLivedata.postSuccess(responseStatus.data.results.map { toFactUI(it) }
                        .filter { !it.title.isNullOrEmpty() })
                }
                Result.Status.ERROR -> {
                    _factsLivedata.postError(responseStatus.message.toString())
                }
            }
        }
    }

    //Load data from Cache
    fun getFactsCache() {
        _factsLivedata.postLoading()
        scope.launch {
            _factsLivedata.postSuccess(dao.getFacts().map { toFactUI(it) })
        }
    }

    fun observeFactsList(connectionAvailable: Boolean): LiveData<Result<List<FactUI>>> {
        if (connectionAvailable) {
            getFactsNetworkPersist()
        } else {
            getFactsCache()
        }
        return _factsLivedata
    }

    //Cancel Coroutine scope
    fun cancelAllRequests() {
        parentJob.cancelChildren()
    }
}