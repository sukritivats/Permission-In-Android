package com.example.permissions

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.viewpager2.widget.ViewPager2
import com.example.permissions.databinding.ActivitySecondBinding
class SecondActivity : AppCompatActivity() {
    private var binding: ActivitySecondBinding? = null

    private val pickPdfLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val selectedPdfUri: Uri? = result.data?.data
                if (selectedPdfUri != null) {
                    Toast.makeText(this, "Pdf is showing", Toast.LENGTH_SHORT).show()
                    displayPdf(selectedPdfUri)
                } else {
                    Toast.makeText(this, "Invalid PDF file", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "No PDF file selected", Toast.LENGTH_SHORT).show()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        binding?.activityThird?.setOnClickListener(){
            startActivity(Intent(this,ThirdActivity::class.java))
        }


        binding?.btnPdf?.setOnClickListener {
            if (checkPermission()) {
                pickPdfFile()
            } else {
//                requestPermission()
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),0)
            }
        }

        binding?.btnCall?.setOnClickListener(){
            if(checkCallPermission())
                call()
            else
            {
                ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.CALL_PHONE),0)
            }
        }

        binding?.btnLocation?.setOnClickListener(){
            if(checkLocationPermission())
                openGoogleMaps()
            else
            {
                ActivityCompat.requestPermissions(this,
                    arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                    0)
            }
        }
    }

    // call permissions

    private fun checkCallPermission():Boolean{
        return ActivityCompat.checkSelfPermission(this,android.Manifest.permission.CALL_PHONE)==PackageManager.PERMISSION_GRANTED
    }

    private fun call() {

        val phoneNumber = "1234567890"
        val callIntent = Intent(Intent.ACTION_DIAL)
        callIntent.data = Uri.parse("tel:$phoneNumber")

        if (callIntent.resolveActivity(packageManager) != null) {
            if (checkCallPermission()) {
                startActivity(callIntent)
            } else {
                ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.CALL_PHONE), 0)
            }
        } else {
            Toast.makeText(this, "No app to handle call action", Toast.LENGTH_SHORT).show()
        }
    }

    // location permission

    private fun checkLocationPermission():Boolean{
        return ActivityCompat.checkSelfPermission(this,
            android.Manifest.permission.ACCESS_FINE_LOCATION)==PackageManager.PERMISSION_GRANTED
    }

    private fun openGoogleMaps() {
        val latitude = 37.7749
        val longitude = -122.4194

        val gmmIntentUri: Uri = Uri.parse("geo:$latitude,$longitude")
        val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
        mapIntent.setPackage("com.google.android.apps.maps")
        startActivity(mapIntent)
    }

    // pdf file

    private fun pickPdfFile() {
        pickPdfLauncher.launch(Intent(Intent.ACTION_GET_CONTENT).apply {
            type = "application/pdf"
            addCategory(Intent.CATEGORY_OPENABLE)
        })
    }

    private fun checkPermission(): Boolean {
        return ActivityCompat.checkSelfPermission(
            this,
            android.Manifest.permission.READ_EXTERNAL_STORAGE
        ) == PackageManager.PERMISSION_GRANTED
    }

//    private fun requestPermission() {
//        if (!checkPermission()) {
//            ActivityCompat.requestPermissions(
//                this,
//                arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),
//                PERMISSION_REQUEST_CODE
//            )
//        } else {
//            pickPdfFile()
//        }
//    }

    private fun displayPdf(pdfUri: Uri) {
        val webView: WebView = findViewById(R.id.WebView)
        webView.settings.javaScriptEnabled = true

        webView.webViewClient = WebViewClient()

        // Load the PDF file into the WebView
        webView.loadUrl("file:///android_asset/pdfviewer/viewer.html?file=$pdfUri")
    }





}