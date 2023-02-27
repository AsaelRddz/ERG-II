package com.erg_iiapp.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.erg_iiapp.R
import com.erg_iiapp.databinding.ActivityAccountBinding
import com.google.firebase.auth.FirebaseAuth

class AccountActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAccountBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAccountBinding.inflate(layoutInflater)
        setContentView(binding.root)

        title = getString(R.string.Cuenta)

        binding.twEmail.text = FirebaseAuth.getInstance().currentUser?.email
        binding.changePass.setOnClickListener {
            startActivity(Intent(this, UpdatePassActivity::class.java))
        }
    }
}