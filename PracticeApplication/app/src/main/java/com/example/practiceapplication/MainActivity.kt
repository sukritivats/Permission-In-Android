package com.example.practiceapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.practiceapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var isPhoneMode = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvContinueEmail.setOnClickListener(){
            if(isPhoneMode)
            {
                binding.etEmail.visibility = View.VISIBLE
                binding.etPassword.visibility = View.VISIBLE
                binding.etPhone.visibility = View.GONE
                binding.tvContinueEmail.visibility = View.GONE
                binding.tvContinuePhone.visibility=View.VISIBLE
                isPhoneMode = !isPhoneMode
            }

        }
        binding.tvContinuePhone.setOnClickListener(){
            if(!isPhoneMode){
                binding.etEmail.visibility = View.GONE
                binding.etPassword.visibility = View.GONE
                binding.etPhone.visibility = View.VISIBLE
                binding.tvContinueEmail.visibility = View.VISIBLE
                binding.tvContinuePhone.visibility=View.GONE
                isPhoneMode = !isPhoneMode
            }
        }

    }

}