package com.example.coroutines

import android.annotation.SuppressLint
import android.content.Intent
import android.nfc.Tag
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import com.example.coroutines.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.job
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import kotlinx.coroutines.yield
import kotlin.concurrent.thread
import kotlin.math.log

class MainActivity : AppCompatActivity() {

    private lateinit var counterText:TextView
    var binding:ActivityMainBinding?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        GlobalScope.launch(Dispatchers.Main) {
             function()
        }
        binding?.btnDataStore?.setOnClickListener {
            startActivity(Intent(this,DataStore::class.java))
        }
        binding?.btnSharedPreference?.setOnClickListener {
            startActivity(Intent(this,SharedPreference::class.java))
        }


    }

//    private suspend fun function() {
//        val parentJob = GlobalScope.launch(Dispatchers.Main){
//            Log.e("Parent","Parent- ${coroutineContext}")
//            val childJob =launch(Dispatchers.IO){
//                Log.e("child","child- ${coroutineContext}")
//                // by default it will take its parent context
//            }
//        }
//    }

//    private suspend fun function() {
//        Log.e("First","first")
//        GlobalScope.launch(Dispatchers.IO) {
//            delay(1000)
//            Log.e("Second","second")
//        }
//        Log.e("Third","third")
//    }

    private suspend fun function() {
        Log.e("First","first")
        withContext(Dispatchers.IO) {
            delay(1000)
            Log.e("Second","second")
        }
        Log.e("Third","third")
    }





}

// private suspend fun function()= runBlocking(Dispatchers.IO) {
//        Log.e("First","first")
//        GlobalScope.launch(Dispatchers.IO) {
//            delay(1000)
//            Log.e("Second","second")
//        }
//        Log.e("Third","third")
//    }

//No, the provided code is not correct because it's misusing runBlocking and GlobalScope.launch.
// Let me explain:
//
//runBlocking Function:
//
//The runBlocking function is a coroutine builder used to start a new coroutine and block the current thread until the coroutine completes. It is typically used in tests or in main functions where you want to block the execution of the program.
//It's not recommended to use runBlocking in production code, especially on the main thread or in suspending functions, as it can lead to blocking and performance issues.
//GlobalScope.launch:
//
//GlobalScope.launch launches a new coroutine in the global scope, which means
// the coroutine is not tied to any specific lifecycle. It's generally
// discouraged to use GlobalScope
// in production code because coroutines launched in
// GlobalScope are not automatically canceled when the calling scope is cancelled,
// leading to potential memory leaks or unexpected behavior.