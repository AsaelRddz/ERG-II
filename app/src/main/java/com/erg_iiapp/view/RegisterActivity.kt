package com.erg_iiapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.erg_iiapp.R
import com.erg_iiapp.databinding.ActivityLoginBinding
import com.erg_iiapp.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}