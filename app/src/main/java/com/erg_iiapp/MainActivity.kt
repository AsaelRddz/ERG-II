package com.erg_iiapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import com.erg_iiapp.databinding.ActivityMainBinding
import com.erg_iiapp.view.AccountActivity
import com.erg_iiapp.view.LoginActivity
import com.erg_iiapp.view.ejercicios.BrazoActivity
import com.erg_iiapp.view.ejercicios.EspaldaActivity
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        title = getString(R.string.app_name)
        ejercicios()
    }

    private fun ejercicios() {
        binding.cardEspalda.setOnClickListener {
            startActivity(Intent(this, EspaldaActivity::class.java))
        }

        binding.cardBrazo.setOnClickListener {
            startActivity(Intent(this, BrazoActivity::class.java))
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.btnLogout -> {
                // Cerrar sesion
                FirebaseAuth.getInstance().signOut()
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }

            R.id.btnAcc -> {
                startActivity(Intent(this, AccountActivity::class.java))
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        // super.onBackPressed()
    }
}