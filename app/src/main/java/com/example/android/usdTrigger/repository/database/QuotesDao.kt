package com.example.android.usdTrigger.repository.database

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable

@Dao
interface QoutesDao {

    @Query("SELECT * FROM quotes")
    fun selectQuotes(): Flowable<List<QuoteDB>>

    @Query("SELECT date FROM quotes ORDER BY date DESC LIMIT 1")
    fun selectLastDate(): Observable<Long>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(listQuoteDBS: List<QuoteDB>): Completable

    @Query("DELETE FROM quotes")
    fun deleteQuotes():Completable

    fun saveInDb(listQoutes: List<QuoteDB>):Completable {
        return deleteQuotes().andThen(insertAll(listQoutes))
    }
}