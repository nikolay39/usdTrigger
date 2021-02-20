package com.example.android.usdTrigger.repository.database

import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable


class ReposRoom(private val qoutesDao: QoutesDao) : ReposDb{

    override fun selectQuotes(): Flowable<List<QuoteDB>> {
       return qoutesDao.selectQuotes()
    }
    override fun selectLastDate(): Observable<Long> {
        return qoutesDao.selectLastDate()
    }
    override fun deleteQuotes(): Completable{
        return qoutesDao.deleteQuotes()
    }
    override fun insertAll(listQuoteDBS: List<QuoteDB>): Completable {
        return qoutesDao.insertAll(listQuoteDBS)
    }
    override fun saveInDb(listQuoteDBS: List<QuoteDB>): Completable {
            return qoutesDao.saveInDb(listQuoteDBS)
    }
}
