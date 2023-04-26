package com.erg_iiapp.view.ejercicios

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.erg_iiapp.R
import com.erg_iiapp.databinding.ActivityPiernaBinding
import com.erg_iiapp.model.DatosPierna
import com.erg_iiapp.view.CalculoActivity
import com.google.firebase.FirebaseApp
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.util.*

class PiernaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPiernaBinding
    var firebaseDatabase: FirebaseDatabase? = null
    var databaseReference: DatabaseReference? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPiernaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Iniciar la BD con firebase
        inicializarFirebase()
        click()

        binding.include.title.text = getString(R.string.pierna).uppercase()
    }

    private fun inicializarFirebase() {
        FirebaseApp.initializeApp(this)
        firebaseDatabase = FirebaseDatabase.getInstance()
        databaseReference = firebaseDatabase?.getReference()
    }

    private fun click() {
        for (i in 0 until binding.containerCards.childCount) {
            val child = binding.containerCards.getChildAt(i)

            // Agregar OnClickListener a cada hijo
            child.setOnClickListener {
                val position = binding.containerCards.indexOfChild(it) // obtiene la posición del hijo clickeado

                // obtener info de la tarjeta
                when(position){
                    1 -> {
                        binding.txtDescripcion1.visibility = View.VISIBLE
                        binding.txtDescripcion2.visibility = View.GONE
                        binding.txtDescripcion3.visibility = View.GONE
                        binding.txtDescripcion4.visibility = View.GONE
                        binding.txtDescripcion5.visibility = View.GONE
                        binding.txtDescripcion6.visibility = View.GONE
                        binding.txtDescripcion7.visibility = View.GONE
                    }
                    2 -> {
                        binding.txtDescripcion1.visibility = View.GONE
                        binding.txtDescripcion2.visibility = View.VISIBLE
                        binding.txtDescripcion3.visibility = View.GONE
                        binding.txtDescripcion4.visibility = View.GONE
                        binding.txtDescripcion5.visibility = View.GONE
                        binding.txtDescripcion6.visibility = View.GONE
                        binding.txtDescripcion7.visibility = View.GONE
                    }
                    3 -> {
                        binding.txtDescripcion1.visibility = View.GONE
                        binding.txtDescripcion2.visibility = View.GONE
                        binding.txtDescripcion3.visibility = View.VISIBLE
                        binding.txtDescripcion4.visibility = View.GONE
                        binding.txtDescripcion5.visibility = View.GONE
                        binding.txtDescripcion6.visibility = View.GONE
                        binding.txtDescripcion7.visibility = View.GONE
                    }
                    4 -> {
                        binding.txtDescripcion1.visibility = View.GONE
                        binding.txtDescripcion2.visibility = View.GONE
                        binding.txtDescripcion3.visibility = View.GONE
                        binding.txtDescripcion4.visibility = View.VISIBLE
                        binding.txtDescripcion5.visibility = View.GONE
                        binding.txtDescripcion6.visibility = View.GONE
                        binding.txtDescripcion7.visibility = View.GONE
                    }
                    5 -> {
                        binding.txtDescripcion1.visibility = View.GONE
                        binding.txtDescripcion2.visibility = View.GONE
                        binding.txtDescripcion3.visibility = View.GONE
                        binding.txtDescripcion4.visibility = View.GONE
                        binding.txtDescripcion5.visibility = View.VISIBLE
                        binding.txtDescripcion6.visibility = View.GONE
                        binding.txtDescripcion7.visibility = View.GONE
                    }
                    6 -> {
                        binding.txtDescripcion1.visibility = View.GONE
                        binding.txtDescripcion2.visibility = View.GONE
                        binding.txtDescripcion3.visibility = View.GONE
                        binding.txtDescripcion4.visibility = View.GONE
                        binding.txtDescripcion5.visibility = View.GONE
                        binding.txtDescripcion6.visibility = View.VISIBLE
                        binding.txtDescripcion7.visibility = View.GONE
                    }
                    7 -> {
                        binding.txtDescripcion1.visibility = View.GONE
                        binding.txtDescripcion2.visibility = View.GONE
                        binding.txtDescripcion3.visibility = View.GONE
                        binding.txtDescripcion4.visibility = View.GONE
                        binding.txtDescripcion5.visibility = View.GONE
                        binding.txtDescripcion6.visibility = View.GONE
                        binding.txtDescripcion7.visibility = View.VISIBLE
                    }
                }
            }
        }

        binding.btnAddPierna1.setOnClickListener { add(1) }
        binding.btnAddPierna2.setOnClickListener { add(2) }
        binding.btnAddPierna3.setOnClickListener { add(3) }
        binding.btnAddPierna4.setOnClickListener { add(4) }
        binding.btnAddPierna5.setOnClickListener { add(5) }
        binding.btnAddPierna6.setOnClickListener { add(6) }
        binding.btnAddPierna7.setOnClickListener { add(7) }
    }

    private fun add(position: Int) {
        val p = DatosPierna()
        p.uid = UUID.randomUUID().toString()
        p.datosPierna = position

        // Crear un nodo con los datos en firebase
        databaseReference!!.child("datosPierna").child(p.uid!!).setValue(p)
        startActivity(Intent(Intent(this,CalculoActivity::class.java)))

    }
}