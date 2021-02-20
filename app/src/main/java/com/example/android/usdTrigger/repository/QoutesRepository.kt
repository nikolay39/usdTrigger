package com.example.android.usdTrigger.repository

import com.example.android.usdTrigger.repository.database.QuoteDB
import com.example.android.usdTrigger.repository.database.ReposDb
import com.example.android.usdTrigger.repository.network.ApiService
import com.example.android.usdTrigger.repository.network.QuoteXml
import com.example.android.usdTrigger.repository.network.convertToDb
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import java.util.*
import javax.inject.Inject

class QoutesRepository @Inject constructor(val database: ReposDb, val api:ApiService) {
    companion object {
        const val USD: String = "R01235";
    }

    fun downloadQuotes(  dateStart: String,  dateEnd: String,  ticker: String ): Completable {
        Timber.i("downloadQuotes run")
        return api.getQuotes(dateStart, dateEnd, ticker)
                .subscribeOn(Schedulers.computation())
            .flatMapCompletable { data ->
                Timber.i("load XML")
                val qoutes = data.quotes
                database.saveInDb(
                    qoutes.map { quoteXml ->
                        quoteXml.convertToDb()
                    }
                );
            }

    }
    fun listenChange(): Flowable<List<QuoteDB>>  {
        return database.selectQuotes()
    }
}

