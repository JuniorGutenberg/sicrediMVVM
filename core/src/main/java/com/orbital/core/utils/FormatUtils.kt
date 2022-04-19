package com.orbital.core.utils

import android.annotation.SuppressLint
import androidx.core.util.PatternsCompat
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.text.SimpleDateFormat
import java.util.*


class FormatUtils {
    companion object {
        val APP_LOCALE = Locale("pt", "BR")

        @SuppressLint("SimpleDateFormat")
        fun timeToDate(date: String, sFormat: String): String {
            try {
                val sdf = SimpleDateFormat(sFormat)
                val netDate = Date(date.toLong() )
                return sdf.format(netDate)
            } catch (e: Exception) {
                return e.toString()
            }
        }
        fun formataMoeda(valor: Double?): String? {
            val dfs = DecimalFormatSymbols()
            dfs.currencySymbol = ""
            dfs.monetaryDecimalSeparator = ','
            dfs.groupingSeparator = '.'
            val df =
                DecimalFormat.getCurrencyInstance(APP_LOCALE) as DecimalFormat
            df.decimalFormatSymbols = dfs
            return df.format(valor)
        }
        fun isValidateEmail(email:String):Boolean{
            return email.isNotEmpty() && PatternsCompat.EMAIL_ADDRESS.matcher(email).matches()
        }
    }
}