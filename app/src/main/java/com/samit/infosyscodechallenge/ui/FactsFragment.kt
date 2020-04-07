package com.samit.infosyscodechallenge.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.coronavirus.di.injectViewModel
import com.samit.infosyscodechallenge.R
import com.samit.infosyscodechallenge.data.Result
import com.samit.infosyscodechallenge.data.mapper.toFactUI
import com.samit.infosyscodechallenge.databinding.FragmentFactsBinding
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_facts.*
import timber.log.Timber
import javax.inject.Inject

class FactsFragment : DaggerFragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: FactsViewModel
    private lateinit var binding: FragmentFactsBinding

    private val adapter: FactsAdapter?
        get() = (rv_facts.adapter as? FactsAdapter)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_facts, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
    }

    private fun initUI() {
        binding.isLoading = true
        viewModel = injectViewModel(viewModelFactory)
        binding.executePendingBindings()

        binding.srlFacts.setOnRefreshListener {
            //viewModel.getFactsNetwork()
            Timber.d("Infosys APi Called in Fragment Refresh")
        }
        //viewModel.getFactsNetwork()

        viewModel.facts.observe(this@FactsFragment, Observer { result ->
            //Timber.d("FactsResult::--> $result")
            when (result.status) {
                Result.Status.LOADING -> {
                    binding.isLoading = true
                }
                Result.Status.SUCCESS -> {
                    binding.facts = result.data!!.map { toFactUI(it) }
                    binding.isLoading = false
                    Timber.d("Adapter COunt in Fragment ${adapter?.itemCount}")
                }
                Result.Status.ERROR -> {
                    binding.isLoading = false
                }
            }
        })
    }
}