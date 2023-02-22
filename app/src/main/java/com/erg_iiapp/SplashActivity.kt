package com.erg_iiapp

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.erg_iiapp.databinding.ActivitySplashBinding
import com.erg_iiapp.view.LoginActivity
import com.google.firebase.auth.FirebaseAuth

lateinit var binding: ActivitySplashBinding

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_splash)

        // Schedule the display of the splash screen
        val handler = Handler()
        handler.postDelayed({
            val userActive = FirebaseAuth.getInstance().currentUser

            // Si existe un usuario activo se dirige hacia el main
            if(userActive != null){
                startActivity(Intent(this, MainActivity::class.java))
            } else {
                startActivity(Intent(this, LoginActivity::class.java))
            }
            finish()
        }, 1000) // Delay in milliseconds
    }
}