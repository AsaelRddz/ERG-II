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
            Limpiar()
        }

        botonesInfo()
        calculoPeso()
        calculoTotal()
    }

    private fun calculoPeso() {
        binding.btnpesoCodigo1.setOnClickListener { calculoPierna(1) }
        binding.btnpesoCodigo2.setOnClickListener { calculoPierna(2) }
        binding.btnpesoCodigo3.setOnClickListener { calculoPierna(3) }
    }

    private fun calculoPierna(peso: Int) {
        binding.cardPierna.visibility = View.VISIBLE
        contenedor.add(peso)

        binding.btnpiernaCodigo1.setOnClickListener { calculoEspalda(1) }
        binding.btnpiernaCodigo2.setOnClickListener { calculoEspalda(2) }
        binding.btnpiernaCodigo3.setOnClickListener { calculoEspalda(3) }
        binding.btnpiernaCodigo4.setOnClickListener { calculoEspalda(4) }
        binding.btnpiernaCodigo5.setOnClickListener { calculoEspalda(5) }
        binding.btnpiernaCodigo6.setOnClickListener { calculoEspalda(6) }
        binding.btnpiernaCodigo7.setOnClickListener { calculoEspalda(7) }
    }

    private fun calculoEspalda(pierna: Int) {
        binding.cardEspalda.visibility = View.VISIBLE
        contenedor.add(pierna)

        binding.btnespaldaCodigo1.setOnClickListener { calculoBrazo(1) }
        binding.btnespaldaCodigo2.setOnClickListener { calculoBrazo(2) }
        binding.btnespaldaCodigo3.setOnClickListener { calculoBrazo(3) }
        binding.btnespaldaCodigo4.setOnClickListener { calculoBrazo(4) }
    }

    private fun calculoBrazo(brazo: Int) {
        binding.cardBrazo.visibility = View.VISIBLE
        contenedor.add(brazo)

        binding.btnbrazoCodigo1.setOnClickListener { contenedor.add(1) }
        binding.btnbrazoCodigo2.setOnClickListener { contenedor.add(2) }
        binding.btnbrazoCodigo3.setOnClickListener { contenedor.add(3) }
    }

    private fun calculoTotal() {
        binding.btnCalcular.setOnClickListener {
            if (contenedor.size >= 4){
                when(contenedor[0]){
                    1 -> {
                        Toast.makeText(this,"Hola mundo", Toast.LENGTH_SHORT).show()
                    }
                    2 -> {
                        Toast.makeText(this,"2", Toast.LENGTH_SHORT).show()

                    }
                    3 -> {
                        Toast.makeText(this,"3", Toast.LENGTH_SHORT).show()
                    }
                    else -> {}
                }
            } else {
                Snackbar.make(binding.root, "Faltan campos por seleccionar", Snackbar.LENGTH_SHORT).show()
            }
        }
    }


    private fun Limpiar() {
        contenedor = ArrayList()
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