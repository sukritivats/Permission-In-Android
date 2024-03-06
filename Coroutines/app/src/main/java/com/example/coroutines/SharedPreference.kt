package com.example.coroutines

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.coroutines.databinding.ActivitySharedPreferenceBinding




class SharedPreference : AppCompatActivity() {
    var binding:ActivitySharedPreferenceBinding?=null

    var sharedPref: SharedPreferences? = null
    var sharedPrefEditor: SharedPreferences.Editor? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivitySharedPreferenceBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        sharedPref = this.getSharedPreferences("rajat", MODE_PRIVATE)
        sharedPrefEditor = sharedPref?.edit()

        binding?.btnSave?.setOnClickListener {
            val data = binding?.input?.text.toString()
            saveData(data)
        }
        binding?.btnRead?.setOnClickListener {
            readData()
        }

    }

    private fun saveData(data: String) {
//        val gsonObj = Gson().toJson(data)
        sharedPrefEditor?.putString("data", data)
        sharedPrefEditor?.apply()
        Toast.makeText(this, "Data saved successfully", Toast.LENGTH_SHORT).show()
    }

    fun readData() {
        val gsonData = sharedPref?.getString("data", null)
//        val model = Gson().fromJson(gsonData, String::class.java)
        val toastMessage = "Data: $gsonData"
        Toast.makeText(this, toastMessage, Toast.LENGTH_SHORT).show()
    }
}