package com.samit.infosyscodechallenge.di.builder

import androidx.lifecycle.ViewModelProvider
import com.samit.infosyscodechallenge.di.builder.AppViewModelBuilder
import com.samit.infosyscodechallenge.di.builder.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module(
    includes = [
        (AppViewModelBuilder::class)
    ]
)
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory
}