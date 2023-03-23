package com.erg_iiapp.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
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

        clicks()
    }

    private fun clicks() {
        binding.btnCalcular.setOnClickListener {
            if (binding.txtPierna.text.toString().isNotEmpty() && binding.txtEspalda.text.toString().isNotEmpty() &&
                binding.txtBrazos.text.toString().isNotEmpty() && binding.txtCarga.text.toString().isNotEmpty()){
                calcular()
            } else {
                Toast.makeText(this, "Verifique que ningun campo esta vacio", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnLimpiar.setOnClickListener {
            binding.txtPierna.setText("")
            binding.txtEspalda.setText("")
            binding.txtBrazos.setText("")
            binding.txtCarga.setText("")
        }
    }

    private fun calcular() {
        val valorPierna = Integer.parseInt(binding.txtPierna.text.toString()) - 1
        val valorEspalda = Integer.parseInt(binding.txtEspalda.text.toString()) - 1
        val valorBrazos = Integer.parseInt(binding.txtBrazos.text.toString()) - 1
        val valorCarga = Integer.parseInt(binding.txtCarga.text.toString()) - 1

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