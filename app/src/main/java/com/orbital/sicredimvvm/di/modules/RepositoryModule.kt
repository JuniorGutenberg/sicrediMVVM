package com.orbital.sicredimvvm.di.modules

import android.content.Context
import com.orbital.sicredimvvm.db.dao.EventsDAO
import com.orbital.sicredimvvm.db.service.EventsService
import com.orbital.sicredimvvm.repository.EventsRepository
import com.orbital.sicredimvvm.repository.imple.EventsRepositoryImple
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val repositoryModule = module {
    fun provideRepository(
        eventsService: EventsService,
        context: Context,
        eventsDAO: EventsDAO
    ):EventsRepository{
        return EventsRepositoryImple(eventsService,context, eventsDAO)
    }

    single { provideRepository(get(),androidContext(),get()) }
}