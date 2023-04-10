package com.erg_iiapp.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.erg_iiapp.MainActivity
import com.erg_iiapp.R
import com.erg_iiapp.databinding.ActivityLoginBinding
import com.erg_iiapp.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }

        login()
    }

    private fun login() {
        binding.btnLogIn.setOnClickListener {
            if(binding.etEmail.text!!.isNotEmpty() && binding.etPassword.text!!.isNotEmpty()){
                FirebaseAuth.getInstance()
                    .signInWithEmailAndPassword(binding.etEmail.text.toString(), binding.etPassword.text.toString())
                    .addOnCompleteListener{
                        if (it.isSuccessful){
                            Toast.makeText(this, getString(R.string.welcome), Toast.LENGTH_SHORT).show()

                            startActivity(Intent(this, MainActivity::class.java))
                        } else {
                            Toast.makeText(this, getString(R.string.login_error), Toast.LENGTH_SHORT).show()
                        }
                    }
            }
        }
    }
}