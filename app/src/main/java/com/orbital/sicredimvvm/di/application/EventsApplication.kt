package com.orbital.sicredimvvm.di.application

import android.app.Application
import com.orbital.sicredimvvm.di.modules.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class EventsApplication:Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@EventsApplication)
            modules(
                serviceModule,
                dataBaseModule,
                networkModule,
                repositoryModule,
                viewModelModule
            )
        }
    }
}