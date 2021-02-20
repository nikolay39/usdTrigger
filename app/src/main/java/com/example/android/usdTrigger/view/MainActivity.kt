package com.example.android.usdTrigger.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.android.usdTrigger.App
import com.example.android.usdTrigger.R
import com.example.android.usdTrigger.databinding.ActivityMainBinding
import com.example.android.usdTrigger.di.modules.view.ViewComponent
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    lateinit var viewComponent: ViewComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.d("TestLogEmulator");

        viewComponent = (applicationContext as App)
            .appComponent.viewComponent().create()
        viewComponent.inject(this)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

    }
}
