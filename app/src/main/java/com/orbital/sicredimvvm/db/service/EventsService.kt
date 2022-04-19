package com.orbital.sicredimvvm.db.service

import com.orbital.sicredimvvm.db.model.EventsData
import retrofit2.Response
import retrofit2.http.GET

interface EventsService {
    @GET("events")
    suspend fun getEvents():Response<MutableList<EventsData>>
}