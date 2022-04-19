package com.orbital.sicredimvvm.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.orbital.sicredimvvm.db.dao.EventsDAO
import com.orbital.sicredimvvm.db.model.EventsData

@Database(
    entities = [EventsData::class],
    version = 1,
    exportSchema = false
)
abstract class EventsDataBase:RoomDatabase(){
    abstract val eventsDAO:EventsDAO
}