package com.samit.infosyscodechallenge.data.db

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

/*The purpose of making "Title" unique is to prevent from duplicate entries
* Null values of Titles is never inserted*/
@Entity(
    tableName = RoomContract.TABLE_FACT,
    indices = [Index(
        value = ["title"],
        unique = true
    )]
)
data class FactDB(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String?,
    val description: String?,
    val image: String?
)