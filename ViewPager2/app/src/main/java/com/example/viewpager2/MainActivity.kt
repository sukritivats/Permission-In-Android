package com.example.viewpager2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.example.viewpager2.databinding.ActivityMainBinding
import me.relex.circleindicator.CircleIndicator3

class MainActivity : AppCompatActivity() {
    var binding: ActivityMainBinding?=null
    var items:Array<String> = arrayOf("A","B","C","D","E")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)


        binding?.viewPager2?.adapter=ViewPagerAdapter(items)
        binding?.viewPager2?.orientation=ViewPager2.ORIENTATION_HORIZONTAL

       binding?.indicator?.setViewPager(binding?.viewPager2)


    }
}