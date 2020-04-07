package com.samit.infosyscodechallenge.di.builder

import androidx.lifecycle.ViewModel
import com.samit.infosyscodechallenge.di.qualifier.ViewModelKey
import com.samit.infosyscodechallenge.ui.FactsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class AppViewModelBuilder {
    @Binds
    @IntoMap
    @ViewModelKey(FactsViewModel::class)
    abstract fun bindFactsViewModel(factsViewModel: FactsViewModel): ViewModel
}