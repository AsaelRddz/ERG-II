package com.erg_iiapp.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
    private var contenedor: ArrayList<Int> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCalculoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLimpiar.setOnClickListener {
            limpiar()
        }

        calculoTotal()
        clickPeso()
        botonesInfo()
    }

    private fun clickPeso() {
        binding.toggleButton.addOnButtonCheckedListener { group, checkedId, isChecked ->
            if (isChecked) {
                binding.cardPierna.visibility = View.VISIBLE
                clickPierna()

                when(checkedId) {
                    binding.btnpesoCodigo1.id -> {
                        contenedor.add(1)
                    }
                    binding.btnpesoCodigo2.id -> {
                        contenedor.add(2)
                    }
                    binding.btnpesoCodigo3.id -> {
                        contenedor.add(3)
                    }
                }
            }
        }
    }

    private fun clickPierna() {
        binding.toggleButton2.addOnButtonCheckedListener { group, checkedId, isChecked ->
            if (isChecked) {
                binding.cardEspalda.visibility = View.VISIBLE
                clickEspalda()

                when(checkedId) {
                    binding.btnpiernaCodigo1.id -> {
                        contenedor.add(1)
                    }
                    binding.btnpiernaCodigo2.id -> {
                        contenedor.add(2)
                    }
                    binding.btnpiernaCodigo3.id -> {
                        contenedor.add(3)
                    }
                    binding.btnpiernaCodigo4.id -> {
                        contenedor.add(4)
                    }
                    binding.btnpiernaCodigo5.id -> {
                        contenedor.add(5)
                    }
                    binding.btnpiernaCodigo6.id -> {
                        contenedor.add(6)
                    }
                    binding.btnpiernaCodigo7.id -> {
                        contenedor.add(7)
                    }
                }
            }
        }
    }

    private fun clickEspalda() {
        binding.toggleButton3.addOnButtonCheckedListener { group, checkedId, isChecked ->
            if (isChecked) {
                binding.cardBrazo.visibility = View.VISIBLE
                clickBrazo()

                when(checkedId) {
                    binding.btnespaldaCodigo1.id -> {
                        contenedor.add(1)
                    }
                    binding.btnespaldaCodigo2.id -> {
                        contenedor.add(2)
                    }
                    binding.btnespaldaCodigo3.id -> {
                        contenedor.add(3)
                    }
                    binding.btnespaldaCodigo4.id -> {
                        contenedor.add(4)
                    }
                }
            }
        }
    }

    private fun clickBrazo() {
        binding.toggleButton4.addOnButtonCheckedListener { group, checkedId, isChecked ->
            if (isChecked) {
                when(checkedId) {
                    binding.btnbrazoCodigo1.id -> {
                        contenedor.add(1)
                    }
                    binding.btnbrazoCodigo2.id -> {
                        contenedor.add(2)
                    }
                    binding.btnbrazoCodigo3.id -> {
                        contenedor.add(3)
                    }
                }
            }
        }
    }

    private fun calculoTotal() {
        binding.btnCalcular.setOnClickListener(View.OnClickListener {
            var resultado = 0
            if (contenedor.size >= 4){
                // peso, pierna, espalda, brazo
                // 0      1       2        3

                if(contenedor[1] in 1..3 && contenedor[2] == 1){ // rango 1
                    resultado = 1
                }

                if (contenedor[1] in 4..5 && contenedor[2] == 1) { // rango 2
                    if (contenedor[3] != 3 && contenedor[0] != 3) {
                        resultado = 2
                    } else {
                        resultado = 3
                    }
                }

                if (resultado != 0){
                    val intent = Intent(this, ResultadoCalculoActivity::class.java)
                    intent.putExtra("resultado", resultado)
                    startActivity(intent)
                }

            } else {
                Snackbar.make(binding.root, "Faltan campos por seleccionar", Snackbar.LENGTH_SHORT).show()
            }
        })
    }

    private fun limpiar() {
        contenedor.clear()
        binding.cardBrazo.visibility = View.GONE
        binding.cardEspalda.visibility = View.GONE
        binding.cardPierna.visibility = View.GONE

        binding.toggleButton.clearChecked()
        binding.toggleButton2.clearChecked()
        binding.toggleButton3.clearChecked()
        binding.toggleButton4.clearChecked()
    }
    private fun botonesInfo() {
        binding.logoInfoPeso.setOnClickListener { startActivity(Intent(this, PesoActivity::class.java))}
        binding.logoInfoPierna.setOnClickListener { startActivity(Intent(this, PiernaActivity::class.java))}
        binding.logoInfoEspalda.setOnClickListener { startActivity(Intent(this, EspaldaActivity::class.java))}
        binding.logoInfoBrazo.setOnClickListener { startActivity(Intent(this, BrazoActivity::class.java))}
    }
}