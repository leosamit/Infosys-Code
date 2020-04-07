package com.samit.infosyscodechallenge.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.samit.infosyscodechallenge.data.Result
import com.samit.infosyscodechallenge.data.repo.FactsRepository
import com.samit.infosyscodechallenge.ui.model.FactUI
import javax.inject.Inject

class FactsViewModel @Inject constructor(private val repository: FactsRepository) :
    ViewModel() {

    private val factsLiveData: MutableLiveData<Result<List<FactUI>>> = MutableLiveData()
    var factsList: LiveData<Result<List<FactUI>>> = MutableLiveData()

//    fun getFacts() {
//        Timber.d("Infosys APi Called in ViewModels getFacts")
//        //factsList = repository.getFacts()
//        val result = repository.getFacts()
//        factsList = result
//    }

    fun refreshFacts() {
        repository.getFactsNetwork()
    }

    fun getFactsNetwork() {
        repository.getFactsNetwork()
    }

    //val facts = repository.factsLiveData
    val facts = repository.singleSourceFacts

    //val legoSet by lazy { repository.observeSet(id) }


}