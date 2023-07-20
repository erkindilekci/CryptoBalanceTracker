package com.erkindilekci.util

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import androidx.core.app.NotificationCompat

object Notification {

    fun showNotification(context: Context, price: String) {
        createNotification(context, price)
    }

    private fun createNotification(context: Context, price: String) {

        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val channelPriority = NotificationManager.IMPORTANCE_HIGH

        var channel: NotificationChannel? =
            notificationManager.getNotificationChannel(Constants.CHANNEL_ID)

        if (channel == null) {
            channel = NotificationChannel(
                Constants.CHANNEL_ID,
                Constants.CHANNEL_NAME, channelPriority
            )
            channel.description = Constants.CHANNEL_DESCRIPTION
            notificationManager.createNotificationChannel(channel)
        }

        createNotification(context, price, notificationManager)

    }

    private fun createNotification(
        context: Context,
        price: String,
        notificationManager: NotificationManager
    ) {
        val builder: NotificationCompat.Builder = NotificationCompat
            .Builder(context, Constants.CHANNEL_ID)
            .setContentTitle("Bitcoin Current Price")
            .setContentText("Bitcoin Price :  $price")
            .setAutoCancel(true)

        notificationManager.notify(1, builder.build())
    }
}
