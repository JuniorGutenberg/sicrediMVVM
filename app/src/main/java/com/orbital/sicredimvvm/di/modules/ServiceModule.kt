package com.orbital.sicredimvvm.di.modules

import com.orbital.sicredimvvm.db.service.EventsService
import org.koin.dsl.module
import retrofit2.Retrofit

val serviceModule = module{
    fun provideServiceModule(retrofit: Retrofit):EventsService{
        return retrofit.create(EventsService::class.java)
    }
    single { provideServiceModule(get()) }
}