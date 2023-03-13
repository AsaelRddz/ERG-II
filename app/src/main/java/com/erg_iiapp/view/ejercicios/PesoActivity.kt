package com.erg_iiapp.view.ejercicios

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.erg_iiapp.databinding.ActivityPesoBinding

class PesoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPesoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPesoBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}