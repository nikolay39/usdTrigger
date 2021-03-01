package com.example.android.usdTrigger.repository

import com.example.android.usdTrigger.repository.database.QuoteDB
import com.example.android.usdTrigger.repository.database.ReposDb
import com.example.android.usdTrigger.repository.network.ApiService
import com.example.android.usdTrigger.repository.network.QuoteXml
import com.example.android.usdTrigger.repository.network.ValCurs
import com.example.android.usdTrigger.repository.network.convertToDb
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import java.util.*
import javax.inject.Inject

class QuotesRepository @Inject constructor(val database: ReposDb, val api:ApiService) {
    companion object {
        const val USD: String = "R01235";
    }

    fun downloadQuotes(  dateStart: String,  dateEnd: String,  ticker: String ): Completable {
        Timber.i("downloadQuotes run")
        return api.getQuotes(dateStart, dateEnd, ticker)
                .subscribeOn(Schedulers.computation())
                .materialize()
                .filter{!it.isOnError}
                .dematerialize { data->data}
                .flatMapCompletable {
                    data->
                    Timber.i("load XML")
                    database.insertAll(
                            data.quotes.map { quoteXml ->
                                quoteXml.convertToDb()
                            }
                    );
                }
    }
    fun listenChange(): Flowable<List<QuoteDB>>  {
        Timber.i("listenChange run");
        return database.selectQuotes()
    }
}

