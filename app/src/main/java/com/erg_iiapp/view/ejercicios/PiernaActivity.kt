package com.erg_iiapp.view.ejercicios

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.erg_iiapp.databinding.ActivityPiernaBinding

class PiernaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPiernaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPiernaBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}