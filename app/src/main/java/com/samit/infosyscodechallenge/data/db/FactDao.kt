package com.samit.infosyscodechallenge.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.samit.infosyscodechallenge.data.db.RoomContract.Companion.SELECT_FACTS

@Dao
interface FactDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllFacts(factDB: List<FactDB>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFact(factDB: FactDB)

    @Query(SELECT_FACTS)
    fun getFactsLivedata(): LiveData<List<FactDB>>

    @Query(SELECT_FACTS)
    suspend fun getFacts(): List<FactDB>
}