package com.samit.infosyscodechallenge.di.module

import android.app.Application
import com.samit.infosyscodechallenge.data.db.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DbModule {
    @Singleton
    @Provides
    fun provideDb(app: Application) = AppDatabase.getInstance(app)

    @Singleton
    @Provides
    fun provideFactsDao(db: AppDatabase) = db.factsDao()
}