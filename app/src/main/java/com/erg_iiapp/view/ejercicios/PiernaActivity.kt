package com.erg_iiapp.view.ejercicios

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.erg_iiapp.databinding.ActivityPiernaBinding
import com.erg_iiapp.view.CalculoActivity

class PiernaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPiernaBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPiernaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        click()
    }

    private fun click() {
        for (i in 0 until binding.containerCards.childCount) {
            val child = binding.containerCards.getChildAt(i)

            // Agregar OnClickListener a cada hijo
            child.setOnClickListener {
                val position = binding.containerCards.indexOfChild(it) // obtiene la posición del hijo clickeado
                val intent = Intent(Intent(this,CalculoActivity::class.java))
                intent.putExtra("OpcionPierna", position)
                startActivity(intent)
            }
        }
    }
}