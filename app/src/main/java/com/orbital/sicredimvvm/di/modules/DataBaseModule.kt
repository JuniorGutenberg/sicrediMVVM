package com.orbital.sicredimvvm.di.modules

import android.app.Application
import androidx.room.Room
import com.orbital.sicredimvvm.db.EventsDataBase
import com.orbital.sicredimvvm.db.dao.EventsDAO
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val dataBaseModule = module {
    fun provideDataBase(application: Application):EventsDataBase{
        return Room.databaseBuilder(application,EventsDataBase::class.java,"events")
            .fallbackToDestructiveMigration()
            .build()
    }

    fun provideDataBaseDao(dataBase: EventsDataBase):EventsDAO{
        return dataBase.eventsDAO
    }
    single { provideDataBase(androidApplication()) }
    single { provideDataBaseDao(get()) }
}