package com.example.android.usdTrigger.common.work

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.work.CoroutineWorker
import androidx.work.Operation.SUCCESS
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import com.example.android.usdTrigger.common.receiver.ForecastReceiver
import com.example.android.usdTrigger.repository.QoutesRepository
import retrofit2.HttpException
import timber.log.Timber
import javax.inject.Inject

private val REQUEST_CODE = 0

class RefreshDataWorker   @Inject constructor(val repository: QoutesRepository, appContext : Context, params: WorkerParameters):
    CoroutineWorker(appContext, params) {
    companion object {
        const val WORK_NAME = "RefreshDataWorker"
    }
    private val notifyIntent = Intent(appContext, ForecastReceiver::class.java)
    override suspend fun doWork(): Result {

        return try {
            //repository.downloadQuotes()
                PendingIntent.getBroadcast(
                    applicationContext,
                    REQUEST_CODE,
                    notifyIntent,
                    PendingIntent.FLAG_UPDATE_CURRENT
                )
            //val outputData = workDataOf('true')
            Result.success()
        } catch(e: HttpException) {
            Result.retry()
        }
    }

}
