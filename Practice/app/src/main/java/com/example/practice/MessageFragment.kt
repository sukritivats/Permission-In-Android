package com.example.practice

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.LinearLayoutManager

class MessageFragment : Fragment() {

    private lateinit var newRv:RecyclerView
    private lateinit var newDataList: ArrayList<MessageData>
    lateinit var imageId:Array<Int>
    lateinit var aboutId:Array<String>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_message, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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
            R.drawable.j
        )

        aboutId= arrayOf(
//            (R.string.sukriti).toString(),
            "Sukriti Vats",
            "Lila Patel",
            "Kai Simmons",
            "Eva Chang",
            "Oscar Morales",
            "Amara Singh","Nolan Carter",
            "Leila Khan",
            "Maxim Clarke",
            "Aria Gonzalez"
        )

        newRv = view.findViewById(R.id.message_recycler_view)
        newRv.layoutManager = LinearLayoutManager(requireContext())
        newRv.setHasFixedSize(true)
        newDataList = arrayListOf<MessageData>()
        getUserData()

    }

    private fun getUserData() {
        for (i in imageId.indices) {
            val news = MessageData(imageId[i], aboutId[i])
            newDataList.add(news)
        }
        val adapter = MessageAdapter(newDataList)
        newRv.adapter = adapter
    }

}