package com.example.fragmentsfile

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.fragmentsfile.databinding.ActivityMainBinding

interface DataPassListener {
    fun onDataPassed(data: String)
}

class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        clickHandler()
        loadDefaultFragment()
    }

    private fun loadDefaultFragment() {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        val fragmentOne = Fg1()
        fragmentTransaction.add(R.id.flOne, fragmentOne, "fo")
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }

    private fun clickHandler() {
        binding?.btnClick?.setOnClickListener {
            val fragmentTransaction = supportFragmentManager.beginTransaction()
            val fragmentOne = Fg1()
            fragmentTransaction.add(R.id.flOne, fragmentOne, "fo")
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        }
        binding?.btnClick2?.setOnClickListener {
            val fragmentTransaction = supportFragmentManager.beginTransaction()
            val fragmentOne = Fg2()
//            val bundle = Bundle()
//            bundle.putString("data","hello from f2")
//            fragmentOne.arguments = bundle
            fragmentTransaction.add(R.id.flOne, fragmentOne)
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        }
    }
}


