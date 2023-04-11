package com.erg_iiapp.view

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivity
import com.erg_iiapp.MainActivity
import com.erg_iiapp.R
import com.erg_iiapp.databinding.ActivityAccountBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth

class AccountActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAccountBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAccountBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.twEmail.text = FirebaseAuth.getInstance().currentUser?.email

        menu()
        clicks()
    }

    private fun clicks() {
        binding.changePass.setOnClickListener {
            startActivity(Intent(this, UpdatePassActivity::class.java))
        }

        binding.btnSignout.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Cerrar sesión")
                .setMessage("¿Está seguro que desea salir?")
                .setPositiveButton("Sí", DialogInterface.OnClickListener { _, _ ->
                    FirebaseAuth.getInstance().signOut()
                    startActivity(Intent(this, LoginActivity::class.java))
                })
                .setNegativeButton("No") { _, _ -> }
                .show()
        }
    }

    private fun menu() {
        //inicializamos variables
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        //Set Home selected
        bottomNavigationView.selectedItemId = R.id.account
        //Perform ItemSelectedListenerbtnHome
        bottomNavigationView.setOnNavigationItemSelectedListener(BottomNavigationView.OnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.home -> {
                    startActivity(Intent(applicationContext, MainActivity::class.java))
                    overridePendingTransition(20, 20)
                }
                R.id.calculate -> {
                    startActivity(Intent(applicationContext, CalculoActivity::class.java))
                    overridePendingTransition(20, 20)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.account -> {
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        })
    }

}
