package com.orbital.sicredimvvm.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.orbital.sicredimvvm.db.model.EventsData

@Dao
interface EventsDAO {

    @Query("SELECT * FROM  Eventos")
    fun getEvents():MutableList<EventsData>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addEvents(events:MutableList<EventsData>)
}