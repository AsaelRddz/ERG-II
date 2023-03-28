package com.erg_iiapp.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.erg_iiapp.R
import com.erg_iiapp.databinding.ActivityCalculoBinding
import com.erg_iiapp.view.ejercicios.BrazoActivity
import com.erg_iiapp.view.ejercicios.EspaldaActivity
import com.erg_iiapp.view.ejercicios.PesoActivity
import com.erg_iiapp.view.ejercicios.PiernaActivity
import com.google.android.material.snackbar.Snackbar

class CalculoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCalculoBinding

    val matriz = arrayOf(
        intArrayOf(1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 1, 1, 1, 1, 1, 1),
        intArrayOf(1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 1, 1, 1, 1, 1, 1),
        intArrayOf(1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 3, 2, 2, 3, 1, 1, 1, 1, 1, 2),
        intArrayOf(2, 2, 3, 2, 2, 3, 5, 2, 3, 3, 3, 3, 3, 3, 3, 2, 2, 2, 2, 3, 3),
        intArrayOf(2, 2, 3, 2, 2, 3, 2, 3, 3, 3, 4, 4, 3, 4, 3, 3, 3, 4, 2, 3, 4),
        intArrayOf(3, 3, 4, 2, 2, 3, 3, 3, 3, 3, 4, 4, 4, 4, 4, 4, 4, 4, 2, 3, 4),
        intArrayOf(1, 1, 1, 1, 1, 1, 1, 1, 2, 3, 3, 3, 4, 4, 4, 1, 1, 1, 1, 1, 1),
        intArrayOf(2, 2, 3, 1, 1, 1, 1, 1, 2, 4, 4, 4, 4, 4, 4, 3, 3, 3, 1, 1, 1),
        intArrayOf(2, 2, 3, 1, 1, 1, 2, 3, 3, 4, 4, 4, 4, 4, 4, 4, 4, 4, 1, 1, 1),
        intArrayOf(2, 3, 3, 2, 2, 3, 2, 2, 3, 4, 4, 4, 4, 4, 4, 4, 4, 4, 2, 3, 4),
        intArrayOf(3, 3, 4, 2, 3, 4, 3, 3, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 2, 3, 4),
        intArrayOf(4, 4, 4, 2, 3, 4, 3, 3, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 2, 3, 4))
    val matriz2 = Array(3) {IntArray(3)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCalculoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val resultado1 = intent.getIntExtra("OpcionPierna",0)
        val resultado2 = intent.getIntExtra("OpcionEspalda",0)
        val resultado3 = intent.getIntExtra("OpcionBrazo",0)
        val resultado4 = intent.getIntExtra("OpcionCarga",0)

        if (resultado1 != 0){
            binding.btnPierna.text = resultado1.toString()
            binding.btnPierna.isEnabled = false
        }

        if (resultado2 != 0){
            binding.btnEspalda.text = resultado2.toString()
            binding.btnPierna.isEnabled = false
        }

        if (resultado3 != 0){
            binding.btnBrazos.text = resultado2.toString()
            binding.btnPierna.isEnabled = false
        }

        if (resultado4 != 0){
            binding.btnCarga.text = resultado2.toString()
            binding.btnPierna.isEnabled = false
        }

        clicks()
        intents()
    }

    private fun intents() {
        binding.btnPierna.setOnClickListener { startActivity(Intent(this, PiernaActivity::class.java)) }
        binding.btnEspalda.setOnClickListener { startActivity(Intent(this, EspaldaActivity::class.java)) }
        binding.btnBrazos.setOnClickListener { startActivity(Intent(this, BrazoActivity::class.java)) }
        binding.btnCarga.setOnClickListener { startActivity(Intent(this, PesoActivity::class.java)) }
    }

    private fun clicks() {
        binding.btnCalcular.setOnClickListener {
            if (binding.btnPierna.text.toString() != getString(R.string.cantidad) && binding.btnEspalda.text.toString()  != getString(R.string.cantidad) &&
                binding.btnBrazos.text.toString() != getString(R.string.cantidad) && binding.btnCarga.text.toString() != getString(R.string.cantidad)){
                calcular()
            } else {
                Toast.makeText(this, "Verifique que todos los campos hayan sido llenados", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnLimpiar.setOnClickListener {
            binding.btnPierna.setText(getString(R.string.cantidad))
            binding.btnEspalda.setText(getString(R.string.cantidad))
            binding.btnBrazos.setText(getString(R.string.cantidad))
            binding.btnCarga.setText(getString(R.string.cantidad))
        }
    }

    private fun calcular() {
        val valorPierna = Integer.parseInt(binding.btnPierna.text.toString()) - 1
        val valorEspalda = Integer.parseInt(binding.btnEspalda.text.toString()) - 1
        val valorBrazos = Integer.parseInt(binding.btnBrazos.text.toString()) - 1
        val valorCarga = Integer.parseInt(binding.btnCarga.text.toString()) - 1

        // Matriz que empareja piernas y espalda
        for (fila in 0..2) {
            for (columna in 0..2) {
                when(valorPierna){
                    0 -> {
                        when(valorEspalda) {
                            0 -> matriz2[fila][columna] = matriz[(valorEspalda)+fila][(valorPierna)+columna]
                            1 -> matriz2[fila][columna] = matriz[(valorEspalda+2)+fila][(valorPierna)+columna]                    1 -> matriz2[fila][columna] = matriz[valorEspalda+fila][(valorPierna+3)+columna]
                            2 -> matriz2[fila][columna] = matriz[(valorEspalda+4)+fila][(valorPierna)+columna]
                            3 -> matriz2[fila][columna] = matriz[(valorEspalda+6)+fila][(valorPierna)+columna]
                        }
                    }
                    1 -> {
                        when(valorEspalda) {
                            0 -> matriz2[fila][columna] = matriz[(valorEspalda)+fila][(valorPierna+2)+columna]
                            1 -> matriz2[fila][columna] = matriz[(valorEspalda+2)+fila][(valorPierna+2)+columna]                    1 -> matriz2[fila][columna] = matriz[valorEspalda+fila][(valorPierna+3)+columna]
                            2 -> matriz2[fila][columna] = matriz[(valorEspalda+4)+fila][(valorPierna+2)+columna]
                            3 -> matriz2[fila][columna] = matriz[(valorEspalda+6)+fila][(valorPierna+2)+columna]
                        }
                    }
                    2 -> {
                        when(valorEspalda) {
                            0 -> matriz2[fila][columna] = matriz[(valorEspalda)+fila][(valorPierna+4)+columna]
                            1 -> matriz2[fila][columna] = matriz[(valorEspalda+2)+fila][(valorPierna+4)+columna]                    1 -> matriz2[fila][columna] = matriz[valorEspalda+fila][(valorPierna+3)+columna]
                            2 -> matriz2[fila][columna] = matriz[(valorEspalda+4)+fila][(valorPierna+4)+columna]
                            3 -> matriz2[fila][columna] = matriz[(valorEspalda+6)+fila][(valorPierna+4)+columna]
                        }
                    }
                    3 -> {
                        when(valorEspalda) {
                            0 -> matriz2[fila][columna] = matriz[(valorEspalda)+fila][(valorPierna+6)+columna]
                            1 -> matriz2[fila][columna] = matriz[(valorEspalda+2)+fila][(valorPierna+6)+columna]                    1 -> matriz2[fila][columna] = matriz[valorEspalda+fila][(valorPierna+3)+columna]
                            2 -> matriz2[fila][columna] = matriz[(valorEspalda+4)+fila][(valorPierna+6)+columna]
                            3 -> matriz2[fila][columna] = matriz[(valorEspalda+6)+fila][(valorPierna+6)+columna]
                        }
                    }
                    4 -> {
                        when(valorEspalda) {
                            0 -> matriz2[fila][columna] = matriz[(valorEspalda)+fila][(valorPierna+8)+columna]
                            1 -> matriz2[fila][columna] = matriz[(valorEspalda+2)+fila][(valorPierna+8)+columna]                    1 -> matriz2[fila][columna] = matriz[valorEspalda+fila][(valorPierna+3)+columna]
                            2 -> matriz2[fila][columna] = matriz[(valorEspalda+4)+fila][(valorPierna+8)+columna]
                            3 -> matriz2[fila][columna] = matriz[(valorEspalda+6)+fila][(valorPierna+8)+columna]
                        }
                    }
                    5 -> {
                        when(valorEspalda) {
                            0 -> matriz2[fila][columna] = matriz[(valorEspalda)+fila][(valorPierna+10)+columna]
                            1 -> matriz2[fila][columna] = matriz[(valorEspalda+2)+fila][(valorPierna+10)+columna]                    1 -> matriz2[fila][columna] = matriz[valorEspalda+fila][(valorPierna+3)+columna]
                            2 -> matriz2[fila][columna] = matriz[(valorEspalda+4)+fila][(valorPierna+10)+columna]
                            3 -> matriz2[fila][columna] = matriz[(valorEspalda+6)+fila][(valorPierna+10)+columna]
                        }
                    }
                    6 -> {
                        when(valorEspalda) {
                            0 -> matriz2[fila][columna] = matriz[(valorEspalda)+fila][(valorPierna+12)+columna]
                            1 -> matriz2[fila][columna] = matriz[(valorEspalda+2)+fila][(valorPierna+12)+columna]                    1 -> matriz2[fila][columna] = matriz[valorEspalda+fila][(valorPierna+3)+columna]
                            2 -> matriz2[fila][columna] = matriz[(valorEspalda+4)+fila][(valorPierna+12)+columna]
                            3 -> matriz2[fila][columna] = matriz[(valorEspalda+6)+fila][(valorPierna+12)+columna]
                        }
                    }
                }
            }
        }

        // Matriz que empareja brazos y carga
        for (fila in 0..2) {
            for (columna in 0..2) {
                if (fila == valorBrazos && columna == valorCarga){
                    val intent = Intent(this, ResultadoCalculoActivity::class.java)
                    intent.putExtra("resultado", matriz2[fila][columna])
                    startActivity(intent)
                    break
                }
            }
        }
    }
}