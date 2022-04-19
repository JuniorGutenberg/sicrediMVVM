package com.orbital.sicredimvvm.repository

import com.orbital.core.utils.Result

interface CheckInRepository {
    suspend fun checkIn(eventId:String,name:String,email:String):Result<String>
}