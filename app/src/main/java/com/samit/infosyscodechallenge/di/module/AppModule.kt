package com.example.coronavirus.di.module

import com.samit.infosyscodechallenge.util.CoroutineDispatcherProvider
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.Dispatchers

@Module
class AppModule {
    @Provides
    fun provideCoroutineDispatcher(): CoroutineDispatcherProvider {
        return CoroutineDispatcherProvider(
            Dispatchers.Main,
            Dispatchers.IO,
            Dispatchers.Default
        )
    }
}