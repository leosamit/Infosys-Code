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
import com.samit.infosyscodechallenge.databinding.FragmentFactsBinding
import com.samit.infosyscodechallenge.util.ConnectivityUtil
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_facts.*
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
        viewModel.connectivityAvailable = ConnectivityUtil.isConnected(context!!)

        binding.srlFacts.setOnRefreshListener {
            viewModel.connectivityAvailable = ConnectivityUtil.isConnected(context!!)
            viewModel.fetchNetworkCache()
        }
        viewModel.fetchNetworkCache()
        viewModel.factsList.observe(this@FactsFragment, Observer { result ->
            when (result.status) {
                Result.Status.LOADING -> {
                    binding.isLoading = true
                }
                Result.Status.SUCCESS -> {
                    binding.facts = result.data
                    binding.isLoading = false
                }
                Result.Status.ERROR -> {
                    binding.isLoading = false
                }
            }
        })
    }
}