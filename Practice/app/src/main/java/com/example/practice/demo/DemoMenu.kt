package com.example.practice.demo

import android.app.Activity
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.content.Intent.CATEGORY_DEFAULT
import android.content.Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.content.Intent.FLAG_ACTIVITY_NO_HISTORY
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.widget.PopupMenu
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.practice.R
import com.example.practice.databinding.DemoMenuBinding

class DemoMenu : Fragment() {

    var _binding: DemoMenuBinding? = null
    val binding get() = _binding!!


    private val images = mutableListOf<Uri>()
//    private lateinit var imageAdapter: ImageAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = DemoMenuBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        imageAdapter = ImageAdapter(images)
//        binding.recyclerView.layoutManager = GridLayoutManager(requireContext(), 3)
//        binding.recyclerView.adapter = imageAdapter
        clickHandler()
//        requestResult.launch(Intent(requireContext(), SplashActivity::class.java))
    }

    fun checkCallPermission(): Boolean =
        activity?.checkSelfPermission(android.Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED
    fun checkImagePermission(): Boolean =
        activity?.checkSelfPermission(android.Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED

    private val PICK_IMAGE_REQUEST = 1

    private fun clickHandler() {

        binding.btMenu.setOnClickListener {
            val menu = PopupMenu(requireContext(), it)
            menu.inflate(R.menu.menu_bar)
            menu.setForceShowIcon(true)
            menu.setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.menuMarvel -> {
                        Toast.makeText(requireContext(), "Menu Clicked", Toast.LENGTH_SHORT).show()
                    }

                    else -> {}
                }
                true
            }
            menu.show()
        }

        binding.btCAll.setOnClickListener {
            if (checkCallPermission()) {
                callPhone()
            } else {
                requestPermission.launch(android.Manifest.permission.CALL_PHONE)
            }
        }
        binding.btImage.setOnClickListener {
           openGallery()
        }
    }
    private fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, PICK_IMAGE_REQUEST)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null) {
//            val selectedImage: Uri? = data.data
//            if (selectedImage != null) {
//                images.add(selectedImage)
//                imageAdapter.notifyDataSetChanged()
//            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun callPhone() {
        Toast.makeText(requireContext(), "Calling...", Toast.LENGTH_SHORT).show()
    }

//    private val requestResult =
//        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
//            if (it.resultCode == Activity.RESULT_OK) {
//
//            }
//        }

    var count = 0



    val requestPermission = registerForActivityResult(ActivityResultContracts.RequestPermission()) {
        if (it == true) {
            callPhone()
        } else {
            if (count == 0) {
                count = 1
                askForRationalPermission()
            } else {
                val intent = Intent(ACTION_APPLICATION_DETAILS_SETTINGS).apply {
                    data = Uri.fromParts("package", context?.packageName, null)
                    addCategory(CATEGORY_DEFAULT)
                    addFlags(FLAG_ACTIVITY_NEW_TASK)
                    addFlags(FLAG_ACTIVITY_NO_HISTORY)
                    addFlags(FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS)
                }
                startActivity(intent)
            }
        }
    }

    private fun askForRationalPermission() {
        val dialog = AlertDialog.Builder((context))
        dialog.setTitle("Call Permission")
        dialog.setMessage("Please provide permisison for call")
        dialog.setPositiveButton("OK"
        ) { dialog, which ->
            dialog.dismiss()
            requestPermission.launch(android.Manifest.permission.CALL_PHONE)
        }
        dialog.setNegativeButton("Cancel"
        ) { dialog, which ->
            dialog.dismiss()
        }
        dialog.show()
    }

}