package com.erg_iiapp.view.ejercicios

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
                val p = DatosEspalda()
                p.uid = UUID.randomUUID().toString()
                p.datosEspalda = position+1

                // Crear un nodo con los datos en firebase
                databaseReference!!.child("datosEspalda").child(p.uid!!).setValue(p)
                startActivity(Intent(Intent(this, CalculoActivity::class.java)))
            }
        }
    }
}