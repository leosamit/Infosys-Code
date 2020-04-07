package com.samit.infosyscodechallenge.data.db

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

//
//@Entity(
//    tableName = RoomContract.TABLE_FACT,
//    indices = [(Index(value = arrayOf("id"), unique = true))]
//)
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
    //@PrimaryKey
    //@Ignore nullable: Nullable
    val title: String?,
    val description: String?,
    val image: String?
)