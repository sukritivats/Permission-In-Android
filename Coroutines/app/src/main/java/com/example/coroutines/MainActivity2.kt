package com.example.coroutines

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.yield

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        CoroutineScope(Dispatchers.Main).launch {
            task1()
        }
        CoroutineScope(Dispatchers.Main).launch {
            task2()
        }
    }

    suspend fun task1(){
        Log.e("Task1","Starting task1")
        yield()
        Log.e("Task1","Ending task1")
        // iss operation m  koi task nhi h jisme coroutine ko suspend krna pare
        // isliye suspend function colourless h
    }
    suspend fun task2(){
        Log.e("Task2","Starting task2")
        yield()
        Log.e("Task2","Ending task2")
        // iss operation m  koi task nhi h jisme coroutine ko suspend krna pare
        // but i have given a suspension point using suspension function
        //delay(1000) 1sec, or yield() these gives suspension point
        // isliye suspend function highlighted h
    }
}