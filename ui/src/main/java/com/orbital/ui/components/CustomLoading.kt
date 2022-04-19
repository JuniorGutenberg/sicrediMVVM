package com.orbital.ui.components

import android.app.Dialog
import android.content.Context
import com.orbital.ui.R

class CustomLoading (context: Context): Dialog(context,R.style.Sicredi_Dialog) {
    init {
        init()
    }

    private fun init() {
        setContentView(R.layout.component_loading)
        setCancelable(false)
        setCanceledOnTouchOutside(false)
    }
}