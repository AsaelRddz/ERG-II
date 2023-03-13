package com.erg_iiapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.erg_iiapp.databinding.ActivityCalculoBinding

class CalculoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCalculoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCalculoBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}