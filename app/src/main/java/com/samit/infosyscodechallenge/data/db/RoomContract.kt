package com.samit.infosyscodechallenge.data.db

class RoomContract {

    companion object {
        const val DATABASE_INFOSYS = "Infosys.db"
        const val TABLE_FACT = "Table_Fact"
        private const val SELECT_FROM = "SELECT * FROM "
        private const val ORDER_BY = " ORDER BY id ASC"
        const val SELECT_FACTS = SELECT_FROM + TABLE_FACT + ORDER_BY
    }
}