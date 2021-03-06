package com.example.android.usdTrigger.di.modules.db

import com.example.android.usdTrigger.repository.database.QuotesDao
import com.example.android.usdTrigger.repository.database.ReposDb
import com.example.android.usdTrigger.repository.database.ReposRoom
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [DatabaseModule::class, DbDao::class])
class DbModule {
    @Provides
    @Singleton
    fun reposDb(quotesDao: QuotesDao): ReposDb {
        return ReposRoom(quotesDao)
    }
}
