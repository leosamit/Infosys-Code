package com.example.coronavirus.di.builder

import com.samit.infosyscodechallenge.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = [MainActivityProviders::class])
    abstract fun bindMainActivity(): MainActivity

}