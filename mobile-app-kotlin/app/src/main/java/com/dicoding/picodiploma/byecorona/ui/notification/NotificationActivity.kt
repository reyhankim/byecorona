package com.dicoding.picodiploma.byecorona.ui.notification

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dicoding.picodiploma.byecorona.R
import com.dicoding.picodiploma.byecorona.databinding.ActivityNotificationBinding

class NotificationActivity : AppCompatActivity() {

    private lateinit var notificationBinding: ActivityNotificationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        notificationBinding = ActivityNotificationBinding.inflate(layoutInflater)
        setContentView(notificationBinding.root)

        notificationBinding.icBack.setOnClickListener {
            onBackPressed()
        }
    }
}