package com.example.android.usdTrigger.di.modules.viewmodel

import androidx.lifecycle.ViewModel
import com.example.android.usdTrigger.viewmodel.OverviewViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class MapViewModel {
    @Binds
    @IntoMap
    @ViewModelKey(OverviewViewModel::class)
    abstract fun bindMainViewModel(overviewViewModel: OverviewViewModel): ViewModel
}