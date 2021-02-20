package com.example.android.usdTrigger.repository.network

import java.text.DecimalFormat
import java.text.DecimalFormatSymbols

object CommaDecimalFormat {
    fun getFormat():DecimalFormat{
        val decimalSymbol = DecimalFormatSymbols()
        decimalSymbol.decimalSeparator = ','
        val format = DecimalFormat("0.#");
        format.setDecimalFormatSymbols(decimalSymbol);
        return format;
    }
}