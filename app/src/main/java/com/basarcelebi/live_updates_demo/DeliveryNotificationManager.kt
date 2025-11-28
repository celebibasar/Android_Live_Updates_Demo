package com.basarcelebi.live_updates_demo

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class DeliveryNotificationManager(private val context: Context) {

    companion object {
        private const val CHANNEL_ID = "delivery_updates"
        private const val NOTIFICATION_ID = 1001
    }

    private val notificationManager = NotificationManagerCompat.from(context)

    init {
        createNotificationChannel()
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                "Sipariş Güncellemeleri",
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                description = "Siparişinizin durumu hakkında anlık güncellemeler"
                setShowBadge(true)
            }

            val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(channel)
        }
    }

    fun showDeliveryNotification(status: DeliveryStatus) {
        val intent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }

        val pendingIntent = PendingIntent.getActivity(
            context,
            0,
            intent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )

        val notification = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .setContentTitle("${status.emoji} ${status.title}")
            .setContentText(status.description)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setCategory(NotificationCompat.CATEGORY_STATUS)
            .setContentIntent(pendingIntent)
            .setAutoCancel(false)
            .setOngoing(true)
            .setProgress(100, status.progress, false)
            .apply {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
                    extras.putBoolean("android.support.liveUpdate", true)

                    when (status) {
                        DeliveryStatus.ORDERED -> setShortCriticalText("Alındı")
                        DeliveryStatus.PREPARING -> setShortCriticalText("Hazır")
                        DeliveryStatus.ON_THE_WAY -> setShortCriticalText("Yolda")
                        DeliveryStatus.DELIVERED -> setShortCriticalText("Teslim")
                        DeliveryStatus.CANCELLED -> setShortCriticalText("İptal")
                    }
                }
            }
            .build()

        notification.flags = notification.flags or NotificationCompat.FLAG_ONGOING_EVENT

        try {
            notificationManager.notify(NOTIFICATION_ID, notification)
        } catch (e: SecurityException) {
            e.printStackTrace()
        }
    }

    fun updateDeliveryStatus(status: DeliveryStatus) {
        showDeliveryNotification(status)
    }

    fun cancelNotification() {
        notificationManager.cancel(NOTIFICATION_ID)
    }

    private fun NotificationCompat.Builder.setShortCriticalText(text: String): NotificationCompat.Builder {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
            extras.putCharSequence("android.shortCriticalText", text)
        }
        return this
    }
}