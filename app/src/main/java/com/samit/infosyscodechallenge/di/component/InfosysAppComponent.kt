package com.samit.infosyscodechallenge.di.component

import android.app.Application
import com.samit.infosyscodechallenge.di.builder.ActivityBuilder
import com.samit.infosyscodechallenge.di.module.AppModule
import com.samit.infosyscodechallenge.di.module.ContextModule
import com.samit.infosyscodechallenge.di.module.NetworkModule
import com.samit.infosyscodechallenge.InfosysApp
import com.samit.infosyscodechallenge.di.module.DbModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        AppModule::class,
        NetworkModule::class,
        ContextModule::class,
        ActivityBuilder::class,
        DbModule::class]
)
interface InfosysAppComponent : AndroidInjector<InfosysApp> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): InfosysAppComponent
    }
}