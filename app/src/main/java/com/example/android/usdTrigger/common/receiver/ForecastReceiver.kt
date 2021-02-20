package com.example.android.usdTrigger.common.receiver

import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.content.ContextCompat
import com.example.android.usdTrigger.R
import com.example.android.usdTrigger.common.sendNotification

class ForecastReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {

        val notificationManager = ContextCompat.getSystemService( context,
            NotificationManager::class.java) as NotificationManager
        notificationManager.sendNotification(
            context.getText(R.string.forecast_description_notification).toString(),
            context
        )
    }
}