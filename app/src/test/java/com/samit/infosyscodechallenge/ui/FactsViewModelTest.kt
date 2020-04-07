package com.samit.infosyscodechallenge.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.samit.infosyscodechallenge.data.repo.FactsRepository
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito

@RunWith(JUnit4::class)
class FactsViewModelTest {
    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    private val repository = Mockito.mock(FactsRepository::class.java)
    private var viewModel = FactsViewModel(repository)

    @Test
    fun testNull() {
        MatcherAssert.assertThat(viewModel.connectivityAvailable, CoreMatchers.notNullValue())
        Mockito.verify(repository, Mockito.never())
            .getFactsNetworkPersist()
        Mockito.verify(repository, Mockito.never())
            .getFactsCache()
    }

    @Test
    fun fetchFromNetworkWhenConnectionAvailable() {
        viewModel.connectivityAvailable = false
        viewModel.fetchNetworkCache()
        Mockito.verify(repository, Mockito.never()).getFactsNetworkPersist()
    }

    @Test
    fun fetchFromNetworkWhenConnectionNotAvailable() {
        viewModel.connectivityAvailable = true
        viewModel.fetchNetworkCache()
        Mockito.verify(repository, Mockito.never()).getFactsCache()
    }
}