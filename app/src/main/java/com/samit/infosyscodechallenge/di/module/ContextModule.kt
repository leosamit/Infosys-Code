package com.samit.infosyscodechallenge.di.module

import android.app.Application
import android.content.Context
import com.samit.infosyscodechallenge.di.builder.ViewModelModule
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [ViewModelModule::class])
class ContextModule {

    @Provides
    @Singleton
    fun provideContext(application: Application): Context {
        return application
    }
}
