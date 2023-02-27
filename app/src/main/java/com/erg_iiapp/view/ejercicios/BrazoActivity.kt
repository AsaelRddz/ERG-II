package com.erg_iiapp.view.ejercicios

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.erg_iiapp.databinding.ActivityBrazoBinding

class BrazoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBrazoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBrazoBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}