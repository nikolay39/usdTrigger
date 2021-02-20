package com.example.android.usdTrigger.repository.database

import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable

interface ReposDb {
        fun selectQuotes(): Flowable<List<QuoteDB>>
        fun selectLastDate(): Observable<Long>
        fun deleteQuotes(): Completable
        fun insertAll(listQuoteDBS: List<QuoteDB>):Completable
        fun saveInDb(listQuoteDBS: List<QuoteDB>): Completable
}