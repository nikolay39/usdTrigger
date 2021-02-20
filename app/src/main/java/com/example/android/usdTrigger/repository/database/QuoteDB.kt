package com.example.android.usdTrigger.repository.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.tickaroo.tikxml.annotation.Attribute
import com.tickaroo.tikxml.annotation.PropertyElement
import com.tickaroo.tikxml.annotation.Xml
import kotlinx.serialization.Serializable
@Entity(tableName = "quotes")
@Serializable
data class QuoteDB  (

    val date: Long,
    val value: Float
) {
    @PrimaryKey(autoGenerate = true)
    var id:Int = 0;
}
