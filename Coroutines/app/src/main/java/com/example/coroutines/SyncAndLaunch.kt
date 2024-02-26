package com.example.coroutines

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SyncAndLaunch : AppCompatActivity() {
    private lateinit var counterText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sync_and_launch)

        CoroutineScope(Dispatchers.IO).launch {
            printfollowers()
        }

    }

//    private suspend fun printfollowers() {
//        var followers=0
////        CoroutineScope(Dispatchers.IO).launch {
////           followers=getFollowers()
////        } // isme 1 sec ka delay h to next line execute ho jayegi aur hame output 0 milega
////        Log.e("followers",followers.toString())
//        // if we want to execute this log after finishing above coroutine then we have to use job object
//
//        val job = CoroutineScope(Dispatchers.IO).launch {
//           followers=getFollowers()
//        }
//        job.join()
//        Log.e("followers",followers.toString())
//    }

//        private suspend fun printfollowers() {
//        var followers=0
//        val deffered = CoroutineScope(Dispatchers.IO).async {
//            getFollowers()
//            //here job returns deffered object of int type
//            // "Hello"
//            //here job returns deffered object of string type
//            //because it takes last statement
//        }
//        Log.e("followers", deffered.await().toString())
//    }

//    private suspend fun printfollowers(){
//        val fb=getFollowers()  // 1 sec
//        val insta =getInstaFollowers()  // 4 sec
//        Log.e("followers", "Fb- $fb  insta- $insta")
//    } // it took total 5 seconds

    //we can optimize it by using async
    private suspend fun printfollowers(){
        CoroutineScope(Dispatchers.IO).async {
            val fb=async { getFollowers() } //1 sec
            val insta =async { getInstaFollowers() } //4 sec
            Log.e("followers", "Fb- ${fb.await()}  insta- ${insta.await()}")
        }
    } // it will take maximum 4 sec

    private suspend fun getFollowers(): Int {
        delay(1000)
        return 56
    }
    private suspend fun getInstaFollowers(): Int {
        delay(4000)
        return 100
    }
}