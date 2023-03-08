package com.erg_iiapp.view.ejercicios

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.erg_iiapp.databinding.ActivityEspaldaBinding

class EspaldaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEspaldaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEspaldaBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}