package com.example.android.usdTrigger.di.modules

import com.example.android.usdTrigger.di.modules.db.DbModule
import com.example.android.usdTrigger.repository.database.ReposDb
import com.example.android.usdTrigger.repository.network.ApiService
import com.example.android.usdTrigger.repository.QuotesRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton
@Module(includes = [ DbModule::class, ApiModule::class])
class RepoModule {
    @Singleton
    @Provides
    fun quotesRepo(db: ReposDb, api: ApiService): QuotesRepository {
        return QuotesRepository(db, api);
    }
}