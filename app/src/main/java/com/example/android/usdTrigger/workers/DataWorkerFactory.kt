package com.example.android.usdTrigger.workers

import android.content.Context
import androidx.work.ListenableWorker
import androidx.work.WorkerFactory
import androidx.work.WorkerParameters
import com.example.android.usdTrigger.repository.QuotesRepository

class DataWorkerFactory(
        private val repository: QuotesRepository
) : WorkerFactory() {
    override fun createWorker(
            applicationContext : Context,
            workerClassName: String,
            workerParameters: WorkerParameters
    ): ListenableWorker? {

        return when (workerClassName) {
            LoadDataWorker::class.java.name ->
                LoadDataWorker(applicationContext, workerParameters, repository)
            else ->
                // Return null, so that the base class can delegate to the default WorkerFactory.
                null
        }
    }
}