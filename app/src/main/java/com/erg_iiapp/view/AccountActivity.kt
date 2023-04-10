package com.erg_iiapp.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
        binding.changePass.setOnClickListener {
            startActivity(Intent(this, UpdatePassActivity::class.java))
        }

        menu()
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