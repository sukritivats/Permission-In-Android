package com.example.multiplescreens

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.example.multiplescreens.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    var binding: ActivityMainBinding?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        clickHandler()

    }

    private fun clickHandler() {
        binding?.btn?.setOnClickListener {
            // code to move to 2nd screen
            startActivity(Intent(this,Activity2::class.java))
        }
    }
}
