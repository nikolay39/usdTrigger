package com.example.android.usdTrigger.di.modules.db

import com.example.android.usdTrigger.repository.database.DatabaseManager
import com.example.android.usdTrigger.repository.database.QoutesDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DbDao {
    @Provides
    @Singleton
    fun provideDao(database: DatabaseManager): QoutesDao {
        return database.qoutesDao()
    }
}
