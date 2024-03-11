package com.example.topicpractice

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController
import com.example.topicpractice.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var binding:ActivityMainBinding?=null

    val launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        if(it.resultCode==Activity.RESULT_OK)
        {
           Toast.makeText(this, "one file selected", Toast.LENGTH_SHORT).show()
        }
        else
            Toast.makeText(this, "no files selected", Toast.LENGTH_SHORT).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        binding?.btnPdf?.setOnClickListener(){
            if(checkPermission())
                openPdf()
            else
                ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),0)
        }
    }
    private fun checkPermission():Boolean{
        return ActivityCompat.checkSelfPermission(this,android.Manifest.permission.READ_EXTERNAL_STORAGE)==PackageManager.PERMISSION_GRANTED
    }

    private fun openPdf(){
        launcher.launch(Intent(Intent.ACTION_GET_CONTENT).apply {
            type="application/pdf"
            addCategory(Intent.CATEGORY_OPENABLE)
        })
    }


}

