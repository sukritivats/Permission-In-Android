package com.example.practice.demo

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.practice.R
import com.example.practice.databinding.DemoMenuBinding

class DemoMenu : Fragment(R.layout.demo_menu) {

    var _binding: DemoMenuBinding? = null
    val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = DemoMenuBinding.bind(view)
    }


}