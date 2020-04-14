package com.samit.infosyscodechallenge.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.samit.infosyscodechallenge.MainActivity
import com.samit.infosyscodechallenge.R
import com.samit.infosyscodechallenge.data.Result
import com.samit.infosyscodechallenge.databinding.FragmentFactsBinding
import com.samit.infosyscodechallenge.di.injectViewModel
import com.samit.infosyscodechallenge.util.ConnectivityUtil
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class FactsFragment : DaggerFragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: FactsViewModel
    private lateinit var binding: FragmentFactsBinding

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
            refresh()
        }
        subscribeUI()
    }

    private fun subscribeUI() {
        viewModel.titleResponse.observe(this, Observer {
            (activity as MainActivity?)
                ?.supportActionBar?.title = it
        })

        viewModel.factsList.observe(this@FactsFragment, Observer { result ->
            when (result.status) {
                Result.Status.LOADING -> {
                    binding.isLoading = true
                }
                Result.Status.SUCCESS -> {
                    binding.facts = result.data
                    binding.isLoading = false
                    if (!viewModel.connectivityAvailable && result.data.isNullOrEmpty()) {
                        errorSnackBar().show()
                        (activity as MainActivity?)
                            ?.supportActionBar?.title = resources.getString(R.string.app_name)
                    }
                }
                Result.Status.ERROR -> {
                    binding.isLoading = false
                }
            }
        })
    }

    private fun refresh() {
        viewModel.connectivityAvailable = ConnectivityUtil.isConnected(context!!)
        viewModel.refreshFactsList()
    }

    private fun errorSnackBar(): Snackbar {
        return Snackbar.make(
            binding.root,
            R.string.list_empty_message, Snackbar.LENGTH_INDEFINITE
        ).setAction(R.string.retry) {
            binding.isLoading = true
            errorSnackBar().dismiss()
            refresh()
        }
    }
}