package com.erg_iiapp.view.ejercicios

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.erg_iiapp.R
import com.erg_iiapp.databinding.ActivityBrazoBinding
import com.erg_iiapp.model.DatosBrazos
import com.erg_iiapp.model.DatosEspalda
import com.erg_iiapp.view.CalculoActivity
import com.google.firebase.FirebaseApp
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.util.*

class BrazoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBrazoBinding
    var firebaseDatabase: FirebaseDatabase? = null
    var databaseReference: DatabaseReference? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBrazoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        click()
        inicializarFirebase()

        binding.include.title.text = getString(R.string.brazo).uppercase()
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

                when(position){
                    1 -> {
                        binding.txtDescripcion1.visibility = View.VISIBLE
                        binding.txtDescripcion2.visibility = View.GONE
                        binding.txtDescripcion3.visibility = View.GONE
                    }
                    2 -> {
                        binding.txtDescripcion1.visibility = View.GONE
                        binding.txtDescripcion2.visibility = View.VISIBLE
                        binding.txtDescripcion3.visibility = View.GONE
                    }
                    3 -> {
                        binding.txtDescripcion1.visibility = View.GONE
                        binding.txtDescripcion2.visibility = View.GONE
                        binding.txtDescripcion3.visibility = View.VISIBLE
                    }
                }
            }
        }

        binding.btnAddBrazo1.setOnClickListener { add(1) }
        binding.btnAddBrazo2.setOnClickListener { add(2) }
        binding.btnAddBrazo3.setOnClickListener { add(3) }
    }

    private fun add(position: Int){
        val p = DatosBrazos()
        p.uid = UUID.randomUUID().toString()
        p.datosBrazos = position

        // Crear un nodo con los datos en firebase
        databaseReference!!.child("datosBrazos").child(p.uid!!).setValue(p)
        startActivity(Intent(Intent(this, CalculoActivity::class.java)))
    }
}