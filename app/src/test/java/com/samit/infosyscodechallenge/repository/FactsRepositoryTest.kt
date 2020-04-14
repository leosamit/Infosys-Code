package com.samit.infosyscodechallenge.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.samit.infosyscodechallenge.api.InfosysService
import com.samit.infosyscodechallenge.data.db.AppDatabase
import com.samit.infosyscodechallenge.data.db.FactDao
import com.samit.infosyscodechallenge.data.repo.FactsRepository
import com.samit.infosyscodechallenge.data.source.FactRemoteDataSource
import com.samit.infosyscodechallenge.util.CoroutineDispatcherProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.ArgumentMatchers
import org.mockito.Mockito

@RunWith(JUnit4::class)
class FactsRepositoryTest {
    private lateinit var repository: FactsRepository
    private val dao = Mockito.mock(FactDao::class.java)
    private val service = Mockito.mock(InfosysService::class.java)
    private val remoteDataSource = FactRemoteDataSource(service)
    private val dispatcherProvider = CoroutineDispatcherProvider(
        Dispatchers.Main,
        Dispatchers.IO, Dispatchers.Default
    )
    private val mockRemoteDataSource = Mockito.spy(remoteDataSource)

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun init() {
        val db = Mockito.mock(AppDatabase::class.java)
        Mockito.`when`(db.factsDao()).thenReturn(dao)
        Mockito.`when`(db.runInTransaction(ArgumentMatchers.any())).thenCallRealMethod()
        repository = FactsRepository(dao, remoteDataSource, dispatcherProvider)
    }

    @Test
    fun loadFactsFromNetwork() {
        runBlocking {
            repository.getFactsNetworkPersist()
            Mockito.verify(dao, Mockito.never()).getFacts()
            Mockito.verifyZeroInteractions(dao)
        }
    }

    @Test
    fun loadFactsCache() {
        runBlocking {
            repository.getFactsCache()
            Mockito.verify(dao, Mockito.never()).getFacts()
            Mockito.verifyZeroInteractions(dao)
        }
    }
}