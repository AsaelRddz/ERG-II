package com.erg_iiapp.view.ejercicios

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.erg_iiapp.R
import com.erg_iiapp.databinding.ActivityEspaldaBinding
import com.erg_iiapp.model.DatosCarga
import com.erg_iiapp.model.DatosEspalda
import com.erg_iiapp.view.CalculoActivity
import com.google.firebase.FirebaseApp
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.util.*

class EspaldaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEspaldaBinding
    var firebaseDatabase: FirebaseDatabase? = null
    var databaseReference: DatabaseReference? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEspaldaBinding.inflate(layoutInflater)
        setContentView(binding.root)
        click()
        inicializarFirebase()

        binding.include.title.text = getString(R.string.espalda).uppercase()
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
                        binding.txtDescripcion4.visibility = View.GONE
                    }
                    2 -> {
                        binding.txtDescripcion1.visibility = View.GONE
                        binding.txtDescripcion2.visibility = View.VISIBLE
                        binding.txtDescripcion3.visibility = View.GONE
                        binding.txtDescripcion4.visibility = View.GONE
                    }
                    3 -> {
                        binding.txtDescripcion1.visibility = View.GONE
                        binding.txtDescripcion2.visibility = View.GONE
                        binding.txtDescripcion3.visibility = View.VISIBLE
                        binding.txtDescripcion4.visibility = View.GONE
                    }
                    4 -> {
                        binding.txtDescripcion1.visibility = View.GONE
                        binding.txtDescripcion2.visibility = View.GONE
                        binding.txtDescripcion3.visibility = View.GONE
                        binding.txtDescripcion4.visibility = View.VISIBLE
                    }
                }
            }
        }

        binding.btnAddEspalda.setOnClickListener { add(1) }
        binding.btnAddEspalda2.setOnClickListener { add(2) }
        binding.btnAddEspalda3.setOnClickListener { add(3) }
        binding.btnAddEspalda4.setOnClickListener { add(4) }
    }

    private fun add(position: Int) {
        val p = DatosEspalda()
        p.uid = UUID.randomUUID().toString()
        p.datosEspalda = position

        // Crear un nodo con los datos en firebase
        databaseReference!!.child("datosEspalda").child(p.uid!!).setValue(p)
        startActivity(Intent(Intent(this, CalculoActivity::class.java)))
    }
}