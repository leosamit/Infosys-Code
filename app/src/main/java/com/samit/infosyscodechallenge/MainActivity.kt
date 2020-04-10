package com.samit.infosyscodechallenge

import android.os.Bundle
import com.samit.infosyscodechallenge.ui.FactsFragment
import dagger.android.support.DaggerAppCompatActivity
import timber.log.Timber

class MainActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Timber.d("THis is called in Main Activity")
        supportActionBar?.title = ""
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, FactsFragment(), "factsFragment").commit()
    }
}
