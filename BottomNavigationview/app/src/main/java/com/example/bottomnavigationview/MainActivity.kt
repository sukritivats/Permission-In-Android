package com.example.bottomnavigationview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import com.example.bottomnavigationview.databinding.ActivityMainBinding
import com.google.android.material.bottomsheet.BottomSheetDialog

class MainActivity : AppCompatActivity() {

    var binding: ActivityMainBinding?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        clickOnbottom()
        binding?.btmSheet?.setOnClickListener {
            showBottomSheet()
        }

    }

    private fun showBottomSheet() {
        val view = LayoutInflater.from(this).inflate(R.layout.btm_sheet, null)
        val bottomSheetDialog = BottomSheetDialog(this)
        bottomSheetDialog.setContentView(view)
        bottomSheetDialog.show()
    }

    private fun clickOnbottom() {
        binding?.btmNavigation?.setOnItemSelectedListener{
            when(it.itemId)
            {
                R.id.home -> replaceWithFragment(Home())
                R.id.music -> replaceWithFragment(Music())
                R.id.apps -> replaceWithFragment(Apps())
                else-> {

                }
            }
            true
        }
    }

    private fun replaceWithFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction=fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame,fragment)
        fragmentTransaction.commit()

    }
}