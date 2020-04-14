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
            .replace(R.id.container, FactsFragment(), resources.getString(R.string.facts_fragment))
            .commit()
    }
}
