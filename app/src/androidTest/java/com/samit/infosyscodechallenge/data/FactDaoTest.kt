package com.samit.infosyscodechallenge.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.samit.infosyscodechallenge.data.db.FactDao
import com.samit.infosyscodechallenge.utils.testFactSetA
import com.samit.infosyscodechallenge.utils.testFactSetB
import kotlinx.coroutines.runBlocking
import org.hamcrest.Matchers
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class FactDaoTest : DbTest() {
    private lateinit var factsDao: FactDao
    private val setA = testFactSetA.copy()
    private val setB = testFactSetB.copy()

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun createDb() {
        factsDao = db.factsDao()
        runBlocking {
            factsDao.insertAllFacts(listOf(setA, setB))
        }
    }

    @Test
    fun testGetFacts() {
        runBlocking {
            val list = (factsDao.getFacts())
            Assert.assertThat(list.size, Matchers.equalTo(2))
            Assert.assertThat(list[0], Matchers.equalTo(setA))
            Assert.assertThat(list[1], Matchers.equalTo(setB))
        }

    }

}