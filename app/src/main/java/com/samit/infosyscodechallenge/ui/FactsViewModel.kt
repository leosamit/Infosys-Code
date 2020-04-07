package com.samit.infosyscodechallenge.ui

import androidx.lifecycle.ViewModel
import com.samit.infosyscodechallenge.data.repo.FactsRepository
import javax.inject.Inject

class FactsViewModel @Inject constructor(private val repository: FactsRepository) :
    ViewModel() {

    //For Single Source of Truth
    val factsSingleSource = repository.singleSourceFacts

    var connectivityAvailable: Boolean = false
    var factsList = repository.factsLiveData

    fun fetchNetworkCache() {
        if (connectivityAvailable) {
            repository.getFactsNetworkPersist()
        } else {
            repository.getFactsCache()
        }
    }

    override fun onCleared() {
        super.onCleared()
        repository.cancelAllRequests()
    }
}