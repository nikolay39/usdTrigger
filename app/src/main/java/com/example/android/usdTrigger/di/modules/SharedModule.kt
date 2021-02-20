package com.example.android.usdTrigger.di.modules

import androidx.work.Configuration
import com.example.android.usdTrigger.workers.IoschedWorkerFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class SharedModule{
    @Singleton
    @Provides
    fun provideWorkManagerConfiguration(
            ioschedWorkerFactory: IoschedWorkerFactory
    ): Configuration {
        return Configuration.Builder()
                .setMinimumLoggingLevel(android.util.Log.DEBUG)
                .setWorkerFactory(ioschedWorkerFactory)
                .build()
    }

}
