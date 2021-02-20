package com.example.android.usdTrigger.di


import android.content.Context
import com.example.android.usdTrigger.App
import com.example.android.usdTrigger.di.modules.*
import com.example.android.usdTrigger.di.modules.view.ViewComponent
import com.example.android.usdTrigger.di.modules.view.ViewModule
import com.example.android.usdTrigger.di.modules.viewmodel.MapViewModel
import com.example.android.usdTrigger.di.modules.viewmodel.ViewModelBuilderModule
import com.example.android.usdTrigger.repository.QoutesRepository
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [RepoModule::class, ViewModelBuilderModule::class,
    ViewModule::class, MapViewModel::class, SharedModule::class])
interface AppComponent {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: Context,
                    ): AppComponent
    }
    fun viewComponent(): ViewComponent.Factory
    fun inject(application: App)
    val quoutesRepo: QoutesRepository

}
