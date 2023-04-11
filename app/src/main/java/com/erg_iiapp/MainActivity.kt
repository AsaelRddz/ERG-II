package com.erg_iiapp

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.erg_iiapp.databinding.ActivityMainBinding
import com.erg_iiapp.view.AccountActivity
import com.erg_iiapp.view.CalculoActivity
import com.erg_iiapp.view.ejercicios.BrazoActivity
import com.erg_iiapp.view.ejercicios.EspaldaActivity
import com.erg_iiapp.view.ejercicios.PesoActivity
import com.erg_iiapp.view.ejercicios.PiernaActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.FirebaseApp
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ejercicios()
        menu()
    }

    private fun menu() {
        //inicializamos variables
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        //Set Home selected
        bottomNavigationView.selectedItemId = R.id.home
        //Perform ItemSelectedListenerbtnHome
        bottomNavigationView.setOnNavigationItemSelectedListener(BottomNavigationView.OnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.home -> {
                    return@OnNavigationItemSelectedListener true
                }
                R.id.calculate -> {
                    startActivity(Intent(applicationContext, CalculoActivity::class.java))
                    overridePendingTransition(20, 20)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.account -> {
                    startActivity(Intent(applicationContext, AccountActivity::class.java))
                    overridePendingTransition(20, 20)
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        })
    }

    private fun ejercicios() {
        binding.cardEspalda.setOnClickListener {
            startActivity(Intent(this, EspaldaActivity::class.java))
        }

        binding.cardBrazo.setOnClickListener {
            startActivity(Intent(this, BrazoActivity::class.java))
        }

        binding.cardPierna.setOnClickListener {
            startActivity(Intent(this, PiernaActivity::class.java))
        }

        binding.cardPeso.setOnClickListener {
            startActivity(Intent(this, PesoActivity::class.java))
        }
    }

    override fun onBackPressed() {
        // super.onBackPressed()
    }
}