package com.orbital.sicredimvvm.db.service

import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface CheckInService {
    @FormUrlEncoded
    @POST("checkin")
    suspend fun checkIn(
        @Field("eventId") eventId:String,
        @Field("name") name:String,
        @Field("email") email:String
    ):Response<String>
}