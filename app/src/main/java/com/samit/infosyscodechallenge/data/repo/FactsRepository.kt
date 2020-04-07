package com.samit.infosyscodechallenge.data.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.samit.infosyscodechallenge.data.Result
import com.samit.infosyscodechallenge.data.db.FactDB
import com.samit.infosyscodechallenge.data.db.FactDao
import com.samit.infosyscodechallenge.data.mapper.toFactDB
import com.samit.infosyscodechallenge.data.mapper.toFactUI
import com.samit.infosyscodechallenge.data.resultLiveData
import com.samit.infosyscodechallenge.data.source.FactRemoteDataSource
import com.samit.infosyscodechallenge.ui.model.FactUI
import com.samit.infosyscodechallenge.util.CoroutineDispatcherProvider
import com.samit.infosyscodechallenge.util.postError
import com.samit.infosyscodechallenge.util.postLoading
import com.samit.infosyscodechallenge.util.postSuccess
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class FactsRepository @Inject constructor(
    private val dao: FactDao,
    private val remoteSource: FactRemoteDataSource,
    private val dispatcherProvider: CoroutineDispatcherProvider
) {
    private val _factsLivedata: MutableLiveData<Result<List<FactUI>>> =
        MutableLiveData()
    val factsLiveData: LiveData<Result<List<FactUI>>> = _factsLivedata

    private val parentJob = Job()
    private val scope = CoroutineScope(dispatcherProvider.mainDispatcher + parentJob)

    val singleSourceFacts = resultLiveData(
        databaseQuery = { dao.getFacts() },
        networkCall = { remoteSource.fetchFacts() },
        saveCallResult = { result ->
            dao.insertAllFacts(result.results.map {
                toFactDB(it)
            }.filter { !it.title.isNullOrEmpty() })
        })

    fun getFacts(): LiveData<Result<List<FactDB>>> {
        Timber.d("Infosys APi Called in FactsRpository")
        return resultLiveData(
            databaseQuery = { dao.getFacts() },
            networkCall = { remoteSource.fetchFacts() },
            saveCallResult = { dao.insertAllFacts(it.results.map { toFactDB(it) }) })
    }

    fun getFactsNetwork() {
        _factsLivedata.postLoading()
        scope.launch {
            val responseStatus = remoteSource.fetchFacts()
            when (responseStatus.status) {
                Result.Status.SUCCESS -> {
                    dao.insertAllFacts(responseStatus.data!!.results.map { toFactDB(it) })
                    Timber.d("Success in Repo")
                    //Timber.d(responseStatus.data.toString())
                    _factsLivedata.postSuccess(responseStatus.data!!.results.map { toFactUI(it) })
                }
                Result.Status.ERROR -> {
                    Timber.d("Error in Repo ${responseStatus.data?.toString()}")

                    _factsLivedata.postError(responseStatus.message.toString())
                }
            }
        }
    }
}