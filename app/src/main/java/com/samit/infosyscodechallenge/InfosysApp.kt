package com.samit.infosyscodechallenge

import com.example.coronavirus.di.component.DaggerInfosysAppComponent
import com.facebook.stetho.Stetho
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import timber.log.Timber


class InfosysApp : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerInfosysAppComponent
            .builder()
            .application(this)
            .build()
    }

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) Stetho.initializeWithDefaults(this)

        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
    }
}