package com.orbital.sicredimvvm.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.orbital.core.utils.Result
import com.orbital.sicredimvvm.db.model.EventsData
import com.orbital.sicredimvvm.repository.EventsRepository
import kotlinx.coroutines.launch

class MainViewModel(private val repository: EventsRepository) : ViewModel() {
     val eventList = MutableLiveData<Result<List<EventsData>?>>()

    fun getEvents(){
        viewModelScope.launch {

            val result = repository.getEvents()
            eventList.value = result
        }
    }
}