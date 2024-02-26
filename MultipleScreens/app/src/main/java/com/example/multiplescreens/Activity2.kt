package com.example.multiplescreens

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.multiplescreens.databinding.Activity2Binding
import com.example.multiplescreens.databinding.ActivityMainBinding

class Activity2 : AppCompatActivity() {

    //allows for the lazy initialization of properties.
    // This means that the property will only be initialized the first time it's accessed,
    // and subsequent accesses will return the same instance without reinitializing it.
    val binding by lazy {
        // This lambda expression will be executed the first time 'binding' is accessed
        Activity2Binding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        clickHandler()
    }

    private fun clickHandler() {
        binding.backbtn.setOnClickListener {

            // It's commonly used when the second activity returns data to the first activity.
            // In this case, no data is being passed back, so an empty Intent is used.
            setResult(Activity.RESULT_OK, Intent().apply {  })
            finish()  // Go back to the previous activity (MainActivity)
            //This finishes the current activity and returns control to the activity that started it.
        }
    }
}
