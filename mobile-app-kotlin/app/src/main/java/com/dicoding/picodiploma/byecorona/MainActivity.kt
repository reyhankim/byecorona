package com.dicoding.picodiploma.byecorona

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.WindowInsets
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import com.dicoding.picodiploma.byecorona.ui.login.LoginActivity

class MainActivity : AppCompatActivity() {

    private lateinit var topAnim: Animation
    private lateinit var bottomAnim: Animation
    private lateinit var logo: ImageView
    private lateinit var text: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
            )
        }

        topAnim = AnimationUtils.loadAnimation(this, R.anim.top_animation)
        bottomAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_animation)

        logo = findViewById(R.id.logo)
        text = findViewById(R.id.text_logo)

        logo.startAnimation(topAnim)
        text.startAnimation(bottomAnim)

        Handler(Looper.getMainLooper()).postDelayed({
            val moveHome = Intent(this@MainActivity, LoginActivity::class.java)
            startActivity(moveHome)
            finish()
        }, SPLASH_SCREEN)
    }

    companion object {
        const val SPLASH_SCREEN = 4000L
    }
}