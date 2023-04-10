package com.erg_iiapp.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.erg_iiapp.R
import com.erg_iiapp.databinding.ActivityUpdatePassBinding
import com.google.firebase.auth.FirebaseAuth

class UpdatePassActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUpdatePassBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdatePassBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val user = FirebaseAuth.getInstance()

        binding.btnChangePass.setOnClickListener {
            if (binding.etPassword.text!!.isNotEmpty()) {
                if (binding.etPassword.text!!.length >= 6){
                    user.currentUser?.updatePassword(binding.etPassword.text.toString())
                        ?.addOnCompleteListener {
                            if (it.isSuccessful){
                                user.signOut()
                                Toast.makeText(this, getString(R.string.change_success), Toast.LENGTH_SHORT).show()
                                startActivity(Intent(this,LoginActivity::class.java))
                                finish()
                            } else {
                                Toast.makeText(this, getString(R.string.change_failure), Toast.LENGTH_SHORT).show()
                            }
                        }
                } else {
                    Toast.makeText(this, getString(R.string.warning_pass), Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}