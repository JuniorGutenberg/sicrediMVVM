package com.orbital.sicredimvvm.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.orbital.core.utils.Result
import com.orbital.sicredimvvm.repository.CheckInRepository
import kotlinx.coroutines.launch

class DetailsViewModel(private val detailsRepository: CheckInRepository):ViewModel() {
     val resultLiveData = MutableLiveData<Result<String>>()

    fun checkIn(name:String,
        email:String,
    eventId:String){
        viewModelScope.launch {
            val result = detailsRepository.checkIn(eventId,name, email)
            resultLiveData.value = result
        }
    }
}