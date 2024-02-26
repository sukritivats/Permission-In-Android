package com.example.fragmentsfile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.fragmentsfile.databinding.FragmentFg2Binding

class Fg2 : Fragment() {

    var binding: FragmentFg2Binding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentFg2Binding.inflate(inflater)
        return binding?.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        cllickHandler()
        getDataFromArguments()
    }

    private fun getDataFromArguments() {
        val data = arguments?.getString("data") ?: "Data is null"
        binding?.tvMsg2?.text = data
    }

    private fun cllickHandler() {

    }
}