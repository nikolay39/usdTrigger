package com.example.android.usdTrigger.di.modules

import android.util.Log
import com.example.android.usdTrigger.repository.network.ApiService
import com.example.android.usdTrigger.repository.network.NetworkInterceptor
import com.tickaroo.tikxml.TikXml
import com.tickaroo.tikxml.converter.htmlescape.HtmlEscapeStringConverter
import com.tickaroo.tikxml.retrofit.TikXmlConverterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
class ApiModule {
    @Singleton
    @Provides
    fun api(retrofit: Retrofit):ApiService {
        return retrofit.create(ApiService::class.java)
    }
    @Named("endpoint")
    @Provides
    fun endpoint():String {
        return "https://www.cbr.ru/scripts/";
    }
    @Provides
    fun retrofit(@Named("endpoint") baseUrl:String,
                 client: OkHttpClient,
                 xmlConverterFactory : TikXmlConverterFactory,
                 addCallAdapterFactory: RxJava2CallAdapterFactory): Retrofit {

        return Retrofit.Builder()
            .addConverterFactory(xmlConverterFactory)
            .addCallAdapterFactory(addCallAdapterFactory)
            .baseUrl(baseUrl)
            .client(client)
            .build();
    }
    @Provides
    fun xmlConverterFactory(): TikXmlConverterFactory {
        return TikXmlConverterFactory.create(
            TikXml.Builder()
                .exceptionOnUnreadXml(false)
                .build()
        )
    }
    @Provides
    fun addCallAdapterFactory(): RxJava2CallAdapterFactory {
        return RxJava2CallAdapterFactory.create()
    }
    @Provides
    fun httploggingInterceptor(): HttpLoggingInterceptor {

        val interceptor: HttpLoggingInterceptor = HttpLoggingInterceptor(object :
            HttpLoggingInterceptor.Logger {
            override fun log (message: String) {
                Timber.tag("usdTriggerHttpClient").d(message);
            }
        })
        interceptor.level = HttpLoggingInterceptor.Level.BODY;
        return interceptor
    }
    @Provides
    fun networkInterceptor():NetworkInterceptor {
        return NetworkInterceptor()
    }
    @Provides
    fun okHttpClient(loggingInterceptor: HttpLoggingInterceptor,
                     networkInterceptor: NetworkInterceptor  ): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)
            .writeTimeout(120, TimeUnit.SECONDS)
            .build();
    }
}