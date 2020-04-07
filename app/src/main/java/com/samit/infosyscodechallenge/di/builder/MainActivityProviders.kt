package com.samit.infosyscodechallenge.di.builder

import com.samit.infosyscodechallenge.ui.FactsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainActivityProviders {

    @ContributesAndroidInjector
    abstract fun provideFactsFragment(): FactsFragment
}