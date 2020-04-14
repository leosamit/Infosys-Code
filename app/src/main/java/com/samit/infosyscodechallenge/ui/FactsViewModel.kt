package com.samit.infosyscodechallenge.ui

import androidx.lifecycle.ViewModel
import com.samit.infosyscodechallenge.data.repo.FactsRepository
import javax.inject.Inject

class FactsViewModel @Inject constructor(private val repository: FactsRepository) :
    ViewModel() {

    var connectivityAvailable: Boolean = false
    var titleResponse = repository.titleResponse

    val factsList by lazy {
        repository.observeFactsList(
            connectivityAvailable
        )
    }

    fun refreshFactsList() {
        repository.observeFactsList(
            connectivityAvailable
        )
    }

    override fun onCleared() {
        super.onCleared()
        repository.cancelAllRequests()
    }
}