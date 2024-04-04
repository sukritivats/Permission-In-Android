package com.example.cofferassignment

import ViewModel
import android.annotation.SuppressLint
import android.graphics.Rect
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.compose.animation.core.Animation
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.cofferassignment.databinding.FragmentPersonalBinding
import com.google.android.material.animation.AnimationUtils

class FragmentPersonal : Fragment() {

    private var binding:FragmentPersonalBinding?=null
    private val viewModel: ViewModel by activityViewModels()
    lateinit var adapter: PersonalRVAdapter
    lateinit var dataList: ArrayList<MyData>
    private val name:Array<String> by lazy {
        arrayOf(
            "Sukriti Vats",
            "Deepak Prahar",
            "Manisha Sisodia",
            "Prakhar Shukla"
        )

    }
    private val dP:Array<String> by lazy {
        arrayOf(
            "SV",
            "DP",
            "MS",
            "PS"
        )

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPersonalBinding.inflate(inflater,container,false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dataList= arrayListOf<MyData>()
        adapter= PersonalRVAdapter(dataList)
        binding?.recyclerView?.adapter=adapter
        binding?.recyclerView?.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        for (i in name.indices)
        {
            dataList.add(MyData(dP[i],name[i]))
        }

        binding?.recyclerView?.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                if (dy < 0) {
                    // Scrolling up, set FAB visibility to true
                    viewModel.fabVisible.value = true
                } else if (dy > 0) {
                    // Scrolling down, set FAB visibility to false
                    viewModel.fabVisible.value = false
                }
            }
        })

    }


}