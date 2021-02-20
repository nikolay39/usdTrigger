package com.example.android.usdTrigger.repository.network

import io.reactivex.Flowable
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url
interface ApiService {
    @GET("XML_dynamic.asp")
    fun getQuotes(@Query("date_req1") date_req1:String, @Query("date_req2") date_req2:String,
                  @Query("VAL_NM_RQ") ticker:String): Observable<ValCurs>;


}
