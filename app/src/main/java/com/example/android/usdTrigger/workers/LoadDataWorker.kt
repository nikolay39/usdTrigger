package com.example.android.usdTrigger.workers

import android.content.Context

import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.android.usdTrigger.repository.QoutesRepository
import com.example.android.usdTrigger.repository.database.ReposRoom
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class LoadDataWorker (applicationContext: Context, workerParameters: WorkerParameters,
    val repository: QoutesRepository ):Worker(applicationContext, workerParameters)
{
    override fun doWork(): Result {

        repository.downloadQuotes(getFirstDayMonth(), getLastDayMonth(), QoutesRepository.USD)
                .andThen(repository.listenChange())
                .subscribe{listQutes->
                    val lastQuote = listQutes.last()
                    if(lastQuote.value > 75.0) {
                        makeStatusNotification("Больше порога", applicationContext)
                    } else {
                        makeStatusNotification("Меньше порога", applicationContext)
                    }
                }
        return Result.success()
    }
    private fun getFirstDayMonth():String {
        val calender = Calendar.getInstance();
        val dateStart = String.format("01/%02d/%d", calender.get(Calendar.MONDAY)+1, calender.get(Calendar.YEAR));
        return dateStart
    }
    private fun getLastDayMonth():String {
        val calender = Calendar.getInstance();
        val dateEnd = String.format("%02d/%02d/%d", calender.get(Calendar.DAY_OF_MONTH),  calender.get(Calendar.MONTH)+1 , calender.get(Calendar.YEAR));
        Timber.i("dateEnd $dateEnd")
        return dateEnd
    }
}