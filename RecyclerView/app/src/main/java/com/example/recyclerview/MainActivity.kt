package com.example.recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var newRecyclerView: RecyclerView
    private lateinit var newArrayList: ArrayList<Data>
    lateinit var imageId : Array<Int>
    lateinit var heading :Array<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imageId= arrayOf(
            R.drawable.a,
            R.drawable.b,
            R.drawable.c,
            R.drawable.d,
            R.drawable.e,
            R.drawable.f,
            R.drawable.g,
            R.drawable.h,
            R.drawable.i,
            R.drawable.j        )

        heading = arrayOf(
            "American child made history in field of journalism.",
            "American child made history in field of journalism.",
            "American child made history in field of journalism.",
            "American child made history in field of journalism.",
            "American child made history in field of journalism.",
            "American child made history in field of journalism.",
            "American child made history in field of journalism.",
            "American child made history in field of journalism.",
            "American child made history in field of journalism.",
            "American child made history in field of journalism."
        )

        newRecyclerView=findViewById(R.id.rv)
        newRecyclerView.layoutManager=LinearLayoutManager(this)
        newRecyclerView.setHasFixedSize(true)
        newArrayList= arrayListOf<Data>()
        getUserData()
    }

    private fun getUserData() {
        for(i in imageId.indices)
        {
            val news=Data(imageId[i],heading[i])
            newArrayList.add(news)
        }
        val adapter = NewsAdapter(newArrayList)
        newRecyclerView.adapter=adapter
        adapter.setonItemClickListener(object :NewsAdapter.onItemClickListener{
            override fun onItemClick(position: Int) {
                Toast.makeText(this@MainActivity, "You clicked on $position", Toast.LENGTH_SHORT).show()
            }
        })
    }
}