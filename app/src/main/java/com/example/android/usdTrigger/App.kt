package com.example.android.usdTrigger

import android.app.Application
import android.content.Context
import android.os.Build
import androidx.work.*
import com.example.android.usdTrigger.di.AppComponent
import com.example.android.usdTrigger.di.DaggerAppComponent
//import com.example.android.usdTrigger.common.work.RefreshDataWorker
//import kotlinx.coroutines.CoroutineScope
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.launch
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Inject

open class App: Application(), Configuration.Provider {
    @Inject lateinit var workerConfiguration: Configuration

    val appComponent: AppComponent by lazy {
        initializeComponent()
    }
    open fun initializeComponent(): AppComponent {
        val appComponent = DaggerAppComponent.factory().create(applicationContext)
        appComponent.inject(this)
        return appComponent
    }
    override fun onCreate() {
        super.onCreate()
        if(BuildConfig.DEBUG)
            Timber.plant(Timber.DebugTree())
    }
    override fun getWorkManagerConfiguration(): Configuration {
        return workerConfiguration
    }
}