package com.example.android.usdTrigger.workers

import android.content.Context

import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.android.usdTrigger.R
import com.example.android.usdTrigger.repository.QuotesRepository
import io.reactivex.Completable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import java.util.*
import java.util.concurrent.TimeUnit

class LoadDataWorker (applicationContext: Context, workerParameters: WorkerParameters,
    val repository: QuotesRepository ):Worker(applicationContext, workerParameters)
{
    override fun doWork(): Result {

       repository.downloadQuotes(getFirstDayMonth(), getLastDayMonth(), QuotesRepository.USD)
                .andThen(repository.listenChange())
                .subscribeOn(Schedulers.computation())
                .flatMapCompletable { listQuotes->
                    Timber.i("dispose")
                    val lastQuote = listQuotes.last()
                    if(lastQuote.value > TREND_LEVEL) {
                        makeStatusNotification(applicationContext.getString(R.string.above_trend), applicationContext)
                    } else {
                        makeStatusNotification(applicationContext.getString(R.string.below_trend), applicationContext)
                    }
                    Completable.complete()
                }
                .subscribe()

        val currentDate = Calendar.getInstance()
        val dueDate = Calendar.getInstance()


        dueDate.set(Calendar.HOUR_OF_DAY, 5)
        dueDate.set(Calendar.MINUTE, 0)
        dueDate.set(Calendar.SECOND, 0)

        if (dueDate.before(currentDate)) {
            dueDate.add(Calendar.HOUR_OF_DAY, 24)
        }

        val timeDiff = dueDate.timeInMillis - currentDate.timeInMillis

        val dailyWorkRequest = OneTimeWorkRequestBuilder<LoadDataWorker>()
                .setInitialDelay(timeDiff, TimeUnit.MILLISECONDS)
                .addTag(TAG_OUTPUT)
                .build()
        WorkManager.getInstance(applicationContext).enqueue(dailyWorkRequest)
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