package com.erg_iiapp.view

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.postDelayed
import com.erg_iiapp.MainActivity
import com.erg_iiapp.R
import com.erg_iiapp.databinding.ActivityCalculoBinding
import com.erg_iiapp.view.ejercicios.BrazoActivity
import com.erg_iiapp.view.ejercicios.EspaldaActivity
import com.erg_iiapp.view.ejercicios.PesoActivity
import com.erg_iiapp.view.ejercicios.PiernaActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.FirebaseApp
import com.google.firebase.database.*

class CalculoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCalculoBinding
    var firebaseDatabase: FirebaseDatabase? = null
    var databaseReference: DatabaseReference? = null

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


        inicializarFirebase()
        clicks()
        vistaInicial()
        menu()

        binding.include.title.text = getString(R.string.calcular).uppercase()
    }

    private fun menu() {
        //inicializamos variables
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        //Set Home selected
        bottomNavigationView.selectedItemId = R.id.calculate
        //Perform ItemSelectedListenerbtnHome
        bottomNavigationView.setOnNavigationItemSelectedListener(BottomNavigationView.OnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.home -> {
                    limpieza()
                    startActivity(Intent(applicationContext, MainActivity::class.java))
                    overridePendingTransition(20, 20)
                }
                R.id.calculate -> {
                    return@OnNavigationItemSelectedListener true
                }
                R.id.account -> {
                    limpieza()
                    startActivity(Intent(applicationContext, AccountActivity::class.java))
                    overridePendingTransition(20, 20)
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        })
    }

    private fun inicializarFirebase() {
        FirebaseApp.initializeApp(this)
        firebaseDatabase = FirebaseDatabase.getInstance()
        databaseReference = firebaseDatabase?.getReference()
    }

    private fun vistaInicial() {
        databaseReference?.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (datos in dataSnapshot.child("datosPierna").children) {
                    val dato = datos.child("datosPierna").getValue(Int::class.java)
                    binding.btnPierna.text = dato?.toString()
                    binding.btnPierna.isEnabled = false
                }
            }

            override fun onCancelled(error: DatabaseError) {
                // Este método se llama si la consulta es cancelada por algún motivo
                binding.btnPierna.setText(getString(R.string.cantidad))
                binding.btnPierna.isEnabled = true
            }
        })

        databaseReference?.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (datos in dataSnapshot.child("datosEspalda").children) {
                    val dato = datos.child("datosEspalda").getValue(Int::class.java)
                    binding.btnEspalda.text = dato.toString()
                    binding.btnEspalda.isEnabled = false
                }
            }

            override fun onCancelled(error: DatabaseError) {
                // Este método se llama si la consulta es cancelada por algún motivo
                binding.btnEspalda.setText(getString(R.string.cantidad))
                binding.btnEspalda.isEnabled = true
            }
        })

        databaseReference?.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (datos in dataSnapshot.child("datosBrazos").children) {
                    val dato = datos.child("datosBrazos").getValue(Int::class.java)
                    binding.btnBrazos.text = dato.toString()
                    binding.btnBrazos.isEnabled = false
                }
            }

            override fun onCancelled(error: DatabaseError) {
                // Este método se llama si la consulta es cancelada por algún motivo
                binding.btnBrazos.setText(getString(R.string.cantidad))
                binding.btnBrazos.isEnabled = true
            }
        })

        databaseReference?.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (datos in dataSnapshot.child("datosCarga").children) {
                    val dato = datos.child("datosCarga").getValue(Int::class.java)
                    binding.btnCarga.text = dato?.toString()
                    binding.btnCarga.isEnabled = false
                }
            }

            override fun onCancelled(error: DatabaseError) {
                // Este método se llama si la consulta es cancelada por algún motivo
                binding.btnPierna.setText(getString(R.string.cantidad))
                binding.btnPierna.isEnabled = true
            }
        })
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
            limpieza()
            startActivity(Intent(this, MainActivity::class.java))
        }

        binding.btnPierna.setOnClickListener { startActivity(Intent(this, PiernaActivity::class.java)) }
        binding.btnEspalda.setOnClickListener { startActivity(Intent(this, EspaldaActivity::class.java)) }
        binding.btnBrazos.setOnClickListener { startActivity(Intent(this, BrazoActivity::class.java)) }
        binding.btnCarga.setOnClickListener { startActivity(Intent(this, PesoActivity::class.java)) }
    }

    private fun limpieza() {
        databaseReference?.child("datosPierna")?.removeValue()
        databaseReference?.child("datosEspalda")?.removeValue()
        databaseReference?.child("datosBrazos")?.removeValue()
        databaseReference?.child("datosCarga")?.removeValue()
    }

    override fun onBackPressed() {
        if (binding.btnPierna.text.toString() == getString(R.string.cantidad) && binding.btnEspalda.text.toString()  == getString(R.string.cantidad) &&
            binding.btnBrazos.text.toString() == getString(R.string.cantidad) && binding.btnCarga.text.toString() == getString(R.string.cantidad)){
            startActivity(Intent(this, MainActivity::class.java))
        }
    }

    override fun onDestroy() {
        limpieza()
        super.onDestroy()
    }
}