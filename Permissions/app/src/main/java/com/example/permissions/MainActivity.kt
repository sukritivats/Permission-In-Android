package com.example.permissions

import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import com.example.permissions.databinding.ActivityMainBinding
import java.io.ByteArrayOutputStream

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var myAdapter: MyAdapter
    private val selectedImages = mutableListOf<Uri>()

    @SuppressLint("NotifyDataSetChanged")
    private val requestGalleryLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            result.data?.clipData?.let { clipData ->
                for (i in 0 until clipData.itemCount) {
                    val imageUri = clipData.getItemAt(i).uri
                    selectedImages.add(imageUri)
                    println("Added image URI: $imageUri")
                }
                myAdapter.notifyDataSetChanged()
            }
        }
    }
    private val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                openGallery()
            } else {
                Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show()
            }
        }

    private val requestCameraPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                openCamera()
            } else {
                Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show()
            }
        }
    @SuppressLint("NotifyDataSetChanged")
    private val requestCameraLauncher= registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val imageBitmap = result.data?.extras?.get("data") as Bitmap?
            imageBitmap?.let {
                // Convert bitmap to URI and add it to selectedImages list
                val imageUri = getImageUriFromBitmap(it)
                selectedImages.add(imageUri)
                // Notify adapter that data set has changed
                myAdapter.notifyDataSetChanged()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSecondActivity.setOnClickListener(){
            startActivity(Intent(this,SecondActivity::class.java))
        }

        binding.btn.setOnClickListener {
            if(checkGalleryPermission())
                openGallery()
            else
                requestGalleryPermission()
        }
        binding.btnCamera.setOnClickListener {
            if(checkCameraPermission())
                openCamera()
            else
                requestCameraPermission()
        }
        setupViewPager()
    }

    private fun setupViewPager() {
        myAdapter = MyAdapter(this, selectedImages)
        binding.ViewPager2.adapter = myAdapter
    }
    // gallery permission

    private fun openGallery(){
        requestGalleryLauncher.launch(Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI).apply { putExtra(Intent.EXTRA_ALLOW_MULTIPLE,true) })
    }

    private fun checkGalleryPermission(): Boolean {
        return ActivityCompat.checkSelfPermission(this,android.Manifest.permission.READ_EXTERNAL_STORAGE)==PackageManager.PERMISSION_GRANTED
    }

    private fun requestGalleryPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(
                this,
                android.Manifest.permission.READ_EXTERNAL_STORAGE
            )
        ) {
            AlertDialog.Builder(this)
                .setTitle("Gallery Permission")
                .setMessage("Please provide permission to access Gallery")
                .setPositiveButton("OK") { dialog, _ ->
                    requestPermissionLauncher.launch(android.Manifest.permission.READ_EXTERNAL_STORAGE)
                }
                .setNegativeButton("Cancel") { dialog, _ ->
                    dialog.dismiss()
                }
                .show()
        } else {
            requestPermissionLauncher.launch(android.Manifest.permission.READ_EXTERNAL_STORAGE)
        }
    }

    // camera permission

    private fun checkCameraPermission():Boolean{
        return ActivityCompat.checkSelfPermission(this,android.Manifest.permission.CAMERA)== PackageManager.PERMISSION_GRANTED
    }

    private fun openCamera(){
        requestCameraLauncher.launch(Intent(MediaStore.ACTION_IMAGE_CAPTURE))
    }
    private fun requestCameraPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(
                this,
                android.Manifest.permission.CAMERA
            )
        ) {
            AlertDialog.Builder(this)
                .setTitle("Gallery Permission")
                .setMessage("Please provide permission to access Gallery")
                .setPositiveButton("OK") { dialog, _ ->
                    requestCameraPermissionLauncher.launch(android.Manifest.permission.CAMERA)
                }
                .setNegativeButton("Cancel") { dialog, _ ->
                    dialog.dismiss()
                }
                .show()
        } else {
            requestCameraPermissionLauncher.launch(android.Manifest.permission.CAMERA)
        }
    }
    private fun getImageUriFromBitmap(bitmap: Bitmap): Uri {
        val bytes = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes)
        val path = MediaStore.Images.Media.insertImage(contentResolver, bitmap, "Title", null)
        return Uri.parse(path)
    }


}


