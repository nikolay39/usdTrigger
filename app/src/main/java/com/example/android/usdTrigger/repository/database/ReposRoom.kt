package com.example.android.usdTrigger.repository.database

import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable


class ReposRoom(private val quotesDao: QuotesDao) : ReposDb{

    override fun selectQuotes(): Flowable<List<QuoteDB>> {
       return quotesDao.selectQuotes()
    }
    override fun selectLastDate(): Observable<Long> {
        return quotesDao.selectLastDate()
    }
    override fun deleteQuotes(): Completable{
        return quotesDao.deleteQuotes()
    }
    override fun insertAll(listQuoteDBS: List<QuoteDB>): Completable {
        return quotesDao.insertAll(listQuoteDBS)
    }
}
