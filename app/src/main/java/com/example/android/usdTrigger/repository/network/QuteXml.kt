package com.example.android.usdTrigger.repository.network

import androidx.room.PrimaryKey
import com.example.android.usdTrigger.repository.database.QuoteDB
import com.tickaroo.tikxml.annotation.Attribute
import com.tickaroo.tikxml.annotation.Element
import com.tickaroo.tikxml.annotation.PropertyElement
import com.tickaroo.tikxml.annotation.Xml
import  com.example.android.usdTrigger.repository.network.CommaDecimalFormat
import java.util.*

@Xml(name = "ValCurs")
data class ValCurs (
    @Element(name="Record")
    val quotes:List<QuoteXml>
)
@Xml(name = "Record")
data class QuoteXml (
    @Attribute(name = "Date")
    var date: String,
    @PropertyElement(name = "Value")
    var value: String,
    @PropertyElement(name = "Nominal")
    var nominal: Int,
) {
    companion object{
        val format = CommaDecimalFormat.getFormat()
        val calendar = Calendar.getInstance();

        fun getDateLong(dateString: String):Long {
            val dateElement = dateString.split(".").map { it.toInt() }
            calendar.set(Calendar.DAY_OF_MONTH, dateElement[0])
            calendar.set(Calendar.MONTH, dateElement[1]-1)
            calendar.set(Calendar.YEAR, dateElement[2])
            return calendar.timeInMillis
        }
    }
}

fun QuoteXml.convertToDb():QuoteDB {
    return QuoteDB(QuoteXml.getDateLong(this.date), QuoteXml.format.parse(this.value).toFloat())
}



