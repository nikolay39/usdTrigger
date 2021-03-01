package com.example.android.usdTrigger.workers

import androidx.work.DelegatingWorkerFactory
import com.example.android.usdTrigger.repository.QuotesRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class IoschedWorkerFactory @Inject constructor(repository: QuotesRepository): DelegatingWorkerFactory() {
   init {
       addFactory(DataWorkerFactory(repository))
   }
}
