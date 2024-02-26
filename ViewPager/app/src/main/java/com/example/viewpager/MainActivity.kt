package com.example.viewpager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.viewpager.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    var binding: ActivityMainBinding?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        val myAdapter =myViewPagerAdapter(supportFragmentManager)
        myAdapter.addFragment(Fragment1(),"One")
        myAdapter.addFragment(Fragment2(),"Two")
        myAdapter.addFragment(Fragment3(),"Three")
        myAdapter.addFragment(Fragment4(),"Four")

        binding?.viewPager?.adapter=myAdapter
        binding?.tabs?.setupWithViewPager(binding?.viewPager)

    }


}