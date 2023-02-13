package com.erg_iiapp

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.erg_iiapp.databinding.ActivitySplashBinding
import com.erg_iiapp.view.LoginActivity

lateinit var binding: ActivitySplashBinding

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_splash)

        // Schedule the display of the splash screen
        val handler = Handler()
        handler.postDelayed({
            // Start the next activity
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }, 1000) // Delay in milliseconds
    }
}