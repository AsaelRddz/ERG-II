package com.erg_iiapp.view.ejercicios

import android.content.Intent
import android.os.Bundle
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
                val p = DatosPierna()
                p.uid = UUID.randomUUID().toString()
                p.datosPierna = position+1

                // Crear un nodo con los datos en firebase
                databaseReference!!.child("datosPierna").child(p.uid!!).setValue(p)
                startActivity(Intent(Intent(this,CalculoActivity::class.java)))
            }
        }
    }
}