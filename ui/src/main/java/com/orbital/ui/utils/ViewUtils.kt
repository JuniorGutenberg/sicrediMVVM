package com.orbital.ui.utils

import android.content.Context
import android.util.Log
import com.orbital.ui.components.CustomLoading
import java.lang.Exception

class ViewUtils {
    companion object{
        private var ieLoading: CustomLoading? = null

        fun showLoading(context:Context){
            try {
                hideLoading()
                ieLoading = CustomLoading(context)

                ieLoading?.show()
            }catch (e: Exception){
                Log.e("Error Loading: ", e.message.toString())
            }
        }
        fun hideLoading(){
            try {
                ieLoading?.dismiss()
            } catch (e: Exception) {
                Log.e("Error Dismiss: ", e.message.toString())
            } finally {
                ieLoading = null
            }
        }
    }
}