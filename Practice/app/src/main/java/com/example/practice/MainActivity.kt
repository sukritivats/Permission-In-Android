package com.example.practice

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.practice.apps.AppsFragment
import com.example.practice.databinding.ActivityMainBinding
import com.example.practice.home.HomeFragment
import com.example.practice.message.MessageFragment

class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null

    val navController by lazy { findNavController(R.id.fragmentContainerView2) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

//        clickBottomItems()

//        replaceFragments(HomeFragment())
    }

//    override fun onSupportNavigateUp(): Boolean {
//        return navController.navigateUp() || super.onSupportNavigateUp()
//    }


    // menu bar
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_bar, menu)
        return true
    }

//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        return when (item.itemId) {
//            R.id.profile -> {
//                navController.navigate(R.id.profileLoginFragment2)
////                binding?.btmNavigation?.visibility = View.GONE
//                true
//            }
//
//            else -> super.onOptionsItemSelected(item)
//        }
//    }


    // bottom navigation
    private fun clickBottomItems() {
        binding?.btmNavigation?.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home -> replaceFragments(HomeFragment())
                R.id.apps -> replaceFragments(AppsFragment())
                R.id.msg -> replaceFragments(MessageFragment())
            }
            true
        }

    }

    private fun replaceFragments(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frameLayout, fragment)
        fragmentTransaction.commit()
    }
}

