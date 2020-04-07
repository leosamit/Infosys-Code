package com.samit.infosyscodechallenge

import android.os.Bundle
import com.samit.infosyscodechallenge.ui.FactsFragment
import dagger.android.support.DaggerAppCompatActivity

class MainActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.title = ""
        supportFragmentManager.beginTransaction()
            .add(R.id.container, FactsFragment(), "factsFragment").commit()
    }
}
