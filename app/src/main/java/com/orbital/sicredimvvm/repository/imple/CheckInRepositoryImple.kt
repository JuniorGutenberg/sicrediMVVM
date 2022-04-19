package com.orbital.sicredimvvm.repository.imple

import android.content.Context
import com.orbital.core.utils.Result
import com.orbital.sicredimvvm.db.service.CheckInService
import com.orbital.sicredimvvm.repository.CheckInRepository
import java.lang.Exception

class CheckInRepositoryImple(
    private val checkInService: CheckInService,
    private val context:Context
):CheckInRepository {

    override suspend fun checkIn(eventId: String, name: String, email: String): Result<String> {
        return try {
            val response = checkInService.checkIn(eventId, name, email)
            if(response.isSuccessful){
                Result.onSucess(response.body())
            }else{
                Result.onError("ERROR API:",response.body())
            }
        }catch (e:Exception){
            Result.onError(e.toString(),null)
        }
    }
}