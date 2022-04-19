package com.orbital.core.utils

import com.orbital.core.enums.Status

data class Result<out T>(val status: Status,val data:T?,val mensage:String?){
    companion object{
        fun <T> onSucess(data:T?):Result<T>{
            return Result(Status.SUCESS,data,null)
        }
        fun <T> onError(msg:String,data:T?):Result<T>{
            return Result(Status.ERROR,data,msg)
        }
        fun <T> onErrorNetwork(data:T?):Result<T>{
            return Result(Status.ERRORNETWORK,data,null)
        }
    }
}
