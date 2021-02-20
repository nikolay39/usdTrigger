package com.example.android.usdTrigger.workers

import androidx.work.DelegatingWorkerFactory
import com.example.android.usdTrigger.repository.QoutesRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class IoschedWorkerFactory @Inject constructor(repository: QoutesRepository): DelegatingWorkerFactory() {
   init {
       addFactory(DataWorkerFactory(repository))
   }
}
