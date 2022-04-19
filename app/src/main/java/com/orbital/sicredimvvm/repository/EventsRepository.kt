package com.orbital.sicredimvvm.repository

import com.orbital.core.utils.Result
import com.orbital.sicredimvvm.db.model.EventsData

interface EventsRepository {
    suspend fun getEvents(): Result<MutableList<EventsData>>
}