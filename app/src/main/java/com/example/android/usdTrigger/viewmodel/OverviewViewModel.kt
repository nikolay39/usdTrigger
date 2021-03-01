package com.example.android.usdTrigger.viewmodel


import android.content.Context
import androidx.lifecycle.*
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.example.android.usdTrigger.repository.database.QuoteDB
import com.example.android.usdTrigger.repository.QuotesRepository
import com.example.android.usdTrigger.workers.LoadDataWorker
import com.example.android.usdTrigger.workers.TAG_OUTPUT
import timber.log.Timber
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class OverviewViewModel @Inject constructor(val repository: QuotesRepository, applicationContext: Context) : ViewModel() {
    lateinit var  quotes: LiveData<List<QuoteDB>>
    private val workManager = WorkManager.getInstance(applicationContext)

    fun init(){
        Timber.i("init vieamodel");
        Timber.i("currentTime ${Calendar.getInstance().get(Calendar.HOUR_OF_DAY)}")
        addDayWorker()
        quotes = LiveDataReactiveStreams.fromPublisher(repository.listenChange());

    }
    private fun addDayWorker() {
        val dailyWorkRequest = OneTimeWorkRequestBuilder<LoadDataWorker>()
                .setInitialDelay(0, TimeUnit.MILLISECONDS)
                .addTag(TAG_OUTPUT)
                .build()
        workManager.enqueue(dailyWorkRequest)
    }
}
