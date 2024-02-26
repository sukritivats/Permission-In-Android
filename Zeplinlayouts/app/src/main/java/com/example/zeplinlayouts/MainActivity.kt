package com.example.zeplinlayouts

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.zeplinlayouts.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    var binding: ActivityMainBinding?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        clickHandler()
    }
    private fun clickHandler() {
            binding?.img?.setOnClickListener {
                startActivity(Intent(this,MainActivity2::class.java))
            }
        }

    }
