package com.dicoding.picodiploma.byecorona.services

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.os.Build
import androidx.core.app.NotificationCompat
import com.dicoding.picodiploma.byecorona.R
import com.dicoding.picodiploma.byecorona.ui.home.MapActivity
import com.dicoding.picodiploma.byecorona.ui.hpv.DetailHPVActivity
import com.dicoding.picodiploma.byecorona.ui.hpv.DetailHPVActivity.Companion.DATA
import com.dicoding.picodiploma.byecorona.utils.DataDummy
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseMessagingService : FirebaseMessagingService() {

    override fun onNewToken(p0: String) {
        super.onNewToken(p0)
    }

    override fun onMessageReceived(p0: RemoteMessage) {
        super.onMessageReceived(p0)
        p0.notification?.let {
            sendNotification(it)
        }
    }

    private fun sendNotification(remoteMessage: RemoteMessage.Notification) {
        val channelId = getString(R.string.default_notification_channel_id)
        val channelName = getString(R.string.default_notification_channel_name)

        val intent = Intent(this, DetailHPVActivity::class.java)
        intent.putExtra(DATA, DataDummy.generateDummyViolations()[0])
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        val pendingIntent = PendingIntent.getActivity(this, 0, intent,
            PendingIntent.FLAG_ONE_SHOT)

        val defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val notificationBuilder = NotificationCompat.Builder(this, channelId)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle(remoteMessage.title)
            .setContentText(remoteMessage.body)
            .setAutoCancel(true)
            .setSound(defaultSoundUri)
            .setContentIntent(pendingIntent)

        val mNotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            val channel = NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_DEFAULT)

            notificationBuilder.setChannelId(channelId)

            mNotificationManager.createNotificationChannel(channel)
        }

        val notification = notificationBuilder.build()

        mNotificationManager.notify(0, notification)
    }

}