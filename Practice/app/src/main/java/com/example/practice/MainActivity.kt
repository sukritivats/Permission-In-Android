package com.example.practice

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.fragment.app.Fragment
import com.example.practice.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        clickBottomItems()
        replaceFragments(HomeFragment())
    }

    // menu bar
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_bar,menu)
        return true
    }

    // bottom navigation
    private fun clickBottomItems() {
        binding?.btmNavigation?.setOnItemSelectedListener {
            when(it.itemId)
            {
                R.id.home-> replaceFragments(HomeFragment())
                R.id.apps-> replaceFragments(AppsFragment())
                R.id.msg-> replaceFragments(MessageFragment())
            }
            true
        }

    }

    private fun replaceFragments(fragment: Fragment)
    {
        val fragmentManager=supportFragmentManager
        val fragmentTransaction=fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frameLayout,fragment)
        fragmentTransaction.commit()
    }
}

