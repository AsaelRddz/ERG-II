package com.erg_iiapp.view

import android.content.Intent
import android.content.res.ColorStateList
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.erg_iiapp.MainActivity
import com.erg_iiapp.R
import com.erg_iiapp.databinding.ActivityResultadoCalculoBinding
import com.google.firebase.FirebaseApp
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class ResultadoCalculoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityResultadoCalculoBinding
    var firebaseDatabase: FirebaseDatabase? = null
    var databaseReference: DatabaseReference? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultadoCalculoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val resultado = intent.getIntExtra("resultado", 0)

        when(resultado){
            1 -> {
                binding.resultadoBg.setBackgroundColor(ContextCompat.getColor(this, R.color.resultado1))
                binding.columna1.text = getString(R.string.postura_normal)
                binding.columna2.text = getString(R.string.no_accion)
            }
            2 -> {
                binding.resultadoBg.setBackgroundColor(ContextCompat.getColor(this, R.color.resultado2))
                binding.columna1.text = getString(R.string.postura_probabilidad)
                binding.columna2.text = getString(R.string.acciones_futuro)
            }
            3 -> {
                binding.resultadoBg.setBackgroundColor(ContextCompat.getColor(this, R.color.resultado3))
                binding.columna1.text = getString(R.string.postura_efectos)
                binding.columna2.text = getString(R.string.acciones_asap)
            }
            4 -> {
                binding.resultadoBg.setBackgroundColor(ContextCompat.getColor(this, R.color.resultado4))
                binding.columna1.text = getString(R.string.carga_dañada)
                binding.columna2.text = getString(R.string.acciones_inmediatas)
            }
            else -> {}
        }

        binding.numeroResultado.text = resultado.toString()
        inicializarFirebase()
        clicks()
    }

    private fun inicializarFirebase() {
        FirebaseApp.initializeApp(this)
        firebaseDatabase = FirebaseDatabase.getInstance()
        databaseReference = firebaseDatabase?.getReference()
    }

    private fun clicks() {
        binding.btnExit.setOnClickListener {
            limpieza()
            startActivity(Intent(this, MainActivity::class.java))
        }
        binding.btnCalculateAgain.setOnClickListener {
            limpieza()
            startActivity(Intent(this, CalculoActivity::class.java))
        }
    }

    fun limpieza() {
        databaseReference?.child("datosPierna")?.removeValue()
        databaseReference?.child("datosEspalda")?.removeValue()
        databaseReference?.child("datosBrazos")?.removeValue()
        databaseReference?.child("datosCarga")?.removeValue()
    }

    override fun onBackPressed() {
        //super.onBackPressed()
    }
}