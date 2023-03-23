package com.erg_iiapp.view

import android.content.Intent
import android.content.res.ColorStateList
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.erg_iiapp.MainActivity
import com.erg_iiapp.R
import com.erg_iiapp.databinding.ActivityResultadoCalculoBinding

class ResultadoCalculoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityResultadoCalculoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultadoCalculoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val resultado = intent.getIntExtra("resultado", 0)

        when(resultado){
            1 -> binding.resultadoBg.setBackgroundColor(ContextCompat.getColor(this, R.color.resultado1))
            2 -> binding.resultadoBg.setBackgroundColor(ContextCompat.getColor(this, R.color.resultado2))
            3 -> binding.resultadoBg.setBackgroundColor(ContextCompat.getColor(this, R.color.resultado3))
            4 -> binding.resultadoBg.setBackgroundColor(ContextCompat.getColor(this, R.color.resultado4))
            else -> {}
        }

        binding.numeroResultado.text = resultado.toString()
    }
}