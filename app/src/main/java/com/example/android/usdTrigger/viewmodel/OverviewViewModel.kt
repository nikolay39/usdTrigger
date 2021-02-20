package com.example.android.usdTrigger.viewmodel


import android.content.Context
import androidx.lifecycle.*
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.example.android.usdTrigger.repository.database.QuoteDB
import com.example.android.usdTrigger.repository.QoutesRepository
import com.example.android.usdTrigger.workers.LoadDataWorker
import com.example.android.usdTrigger.workers.TAG_OUTPUT
import timber.log.Timber
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class OverviewViewModel @Inject constructor( val repository: QoutesRepository, applicationContext: Context) : ViewModel() {
    lateinit var  quotes: LiveData<List<QuoteDB>>
    private val workManager = WorkManager.getInstance(applicationContext)

    fun init(){
        Timber.i("init vieamodel");
        Timber.i("currentTime ${Calendar.getInstance().get(Calendar.HOUR_OF_DAY)}")
        addDayWorker()
        quotes = LiveDataReactiveStreams.fromPublisher(repository.listenChange());

    }
    private fun addDayWorker() {
        val currentDate = Calendar.getInstance()
        val dueDate = Calendar.getInstance()


        dueDate.set(Calendar.HOUR_OF_DAY, 1)
        dueDate.set(Calendar.MINUTE, 7)
        dueDate.set(Calendar.SECOND, 0)

        if (dueDate.before(currentDate)) {
            dueDate.add(Calendar.HOUR_OF_DAY, 24)
        }
        val timeDiff = dueDate.timeInMillis - currentDate.timeInMillis
        val dailyWorkRequest = OneTimeWorkRequestBuilder<LoadDataWorker>()
                .setInitialDelay(timeDiff, TimeUnit.MILLISECONDS)
                .addTag(TAG_OUTPUT)
                .build()
        workManager.enqueue(dailyWorkRequest)
    }
}
