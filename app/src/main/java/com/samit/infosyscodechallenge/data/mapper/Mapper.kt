package com.samit.infosyscodechallenge.data.mapper

import com.samit.infosyscodechallenge.api.FactApi
import com.samit.infosyscodechallenge.data.db.FactDB
import com.samit.infosyscodechallenge.ui.model.FactUI

fun toFactUI(factDB: FactDB): FactUI =
    FactUI(
        title = factDB.title,
        description = factDB.description,
        image = factDB.image
    )

fun toFactDB(factApi: FactApi): FactDB =
    FactDB(
        title = factApi.title,
        description = factApi.description,
        image = factApi.image
    )

fun toFactUI(factApi: FactApi): FactUI =
    FactUI(
        title = factApi.title,
        description = factApi.description,
        image = factApi.image
    )