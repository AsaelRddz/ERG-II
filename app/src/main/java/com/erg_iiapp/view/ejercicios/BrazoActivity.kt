package com.erg_iiapp.view.ejercicios

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.erg_iiapp.databinding.ActivityBrazoBinding
import com.erg_iiapp.view.CalculoActivity

class BrazoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBrazoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBrazoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        click()
    }

    private fun click() {
        for (i in 0 until binding.containerCards.childCount) {
            val child = binding.containerCards.getChildAt(i)

            // Agregar OnClickListener a cada hijo
            child.setOnClickListener {
                val position = binding.containerCards.indexOfChild(it) // obtiene la posición del hijo clickeado
                val intent = Intent(Intent(this, CalculoActivity::class.java))
                intent.putExtra("OpcionBrazo", position)
                startActivity(intent)
            }
        }
    }
}