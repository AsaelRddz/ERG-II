package com.erg_iiapp.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.erg_iiapp.MainActivity
import com.erg_iiapp.R
import com.erg_iiapp.databinding.ActivityLoginBinding
import com.erg_iiapp.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    lateinit var binding: ActivityLoginBinding

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
        title = "Login"

        binding.btnLogIn.setOnClickListener {
            if(binding.etEmail.text!!.isNotEmpty() && binding.etPassword.text!!.isNotEmpty()){
                FirebaseAuth.getInstance()
                    .signInWithEmailAndPassword(binding.etEmail.text.toString(), binding.etPassword.text.toString())
                    .addOnCompleteListener{
                        if (it.isSuccessful){
                            Snackbar.make(binding.root, "Bienvenido", Snackbar.LENGTH_LONG)
                                .show()

                            startActivity(Intent(this, MainActivity::class.java))
                        } else {
                            Snackbar.make(binding.root, "Algo anda mal, intentelo de nuevo mas tarde", Snackbar.LENGTH_LONG)
                                .show()
                        }
                    }
            }
        }
    }
}