package com.example.android.usdTrigger.di.modules.db

import android.content.Context
import androidx.room.Room
import com.example.android.usdTrigger.repository.database.DatabaseManager
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton
@Module
class DatabaseModule {
    @Named("dbName")
    @Provides
    fun provideDbName():String {
        return "usdTrigger";
    }
    @Singleton
    @Provides
    fun provideRoomDatabase(context: Context, @Named("dbName") dbName: String): DatabaseManager {
        return Room.databaseBuilder(context, DatabaseManager::class.java, dbName).build();
    }
}
