package com.erg_iiapp.view

import android.content.Intent
import android.graphics.Color
import android.graphics.ColorFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.erg_iiapp.R
import com.erg_iiapp.databinding.ActivityLoginBinding
import com.erg_iiapp.databinding.ActivityRegisterBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        signup()
    }

    private fun signup() {
        binding.btnRegister.setOnClickListener {
            if(binding.etEmail.text!!.isNotEmpty() && binding.etPassword.text!!.isNotEmpty()){
                if (binding.etPassword.text!!.length >= 6){
                    FirebaseAuth.getInstance()
                        .createUserWithEmailAndPassword(binding.etEmail.text.toString(), binding.etPassword.text.toString())
                        .addOnCompleteListener{
                            if (it.isSuccessful){
                                Toast.makeText(this, getString(R.string.register_success), Toast.LENGTH_SHORT).show()
                                startActivity(Intent(this, LoginActivity::class.java))
                            } else {
                                Toast.makeText(this, getString(R.string.register_error), Toast.LENGTH_SHORT).show()
                            }
                        }
                } else {
                    Toast.makeText(this, getString(R.string.warning_pass), Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}