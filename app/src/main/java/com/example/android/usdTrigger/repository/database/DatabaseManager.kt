package com.example.android.usdTrigger.repository.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [QuoteDB::class], version = 1, exportSchema = false)
abstract class DatabaseManager : RoomDatabase() {
    abstract fun qoutesDao(): QuotesDao
}