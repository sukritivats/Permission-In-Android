package com.example.widgets

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.widgets.databinding.ActivityFormBinding

class FormActivity : AppCompatActivity() {

    val binding by lazy { ActivityFormBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        clickHandler()
    }


    private fun clickHandler() {
        binding.btnResult.setOnClickListener {

            val name = intent.getStringExtra("name")
            val age = intent.getIntExtra("age", 0)

            Log.e("TAG", name ?: "value not given")

            setResult(Activity.RESULT_OK, Intent().apply{

            })
            finish()
        }
    }
}
