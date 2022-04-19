package com.orbital.sicredimvvm.repository.imple

import android.content.Context
import com.orbital.core.utils.NetworkManagerUtil
import com.orbital.core.utils.Result
import com.orbital.sicredimvvm.db.dao.EventsDAO
import com.orbital.sicredimvvm.db.model.EventsData
import com.orbital.sicredimvvm.db.service.EventsService
import com.orbital.sicredimvvm.repository.EventsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception

class EventsRepositoryImple(
    private val eventsService: EventsService,
    private val context: Context,
    private val eventsDAO: EventsDAO):EventsRepository {

    override suspend fun getEvents(): Result<MutableList<EventsData>> {
        if (NetworkManagerUtil.isOnline(context)){
            return try {
                val response = eventsService.getEvents()

                if(response.isSuccessful){
                    response.body()?.let {
                        withContext(Dispatchers.IO){
                            eventsDAO.addEvents(it)
                        }
                    }
                    Result.onSucess(response.body())
                }else{
                    Result.onError("API ERROR:", response.body())
                }
            }catch (e:Exception){
                Result.onError(e.toString(),null)
            }
        }else{
            val dataCache = getEventsCache()
            return if(dataCache.isNullOrEmpty()){
                Result.onSucess(dataCache)
            }else{
                Result.onErrorNetwork(null)
            }
        }
    }
    private suspend fun getEventsCache():MutableList<EventsData>{
        return withContext(Dispatchers.IO){
            eventsDAO.getEvents()
        }
    }
}