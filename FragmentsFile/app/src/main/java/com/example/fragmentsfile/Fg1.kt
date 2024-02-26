package com.example.fragmentsfile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.fragmentsfile.databinding.FragmentFg1Binding


class Fg1 : Fragment() {


    var binding: FragmentFg1Binding? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFg1Binding.inflate(inflater)
        return binding?.root

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        cllickHandler()
    }

    private fun cllickHandler() {
        binding?.btnF1?.setOnClickListener {
            val fragmentTransaction = childFragmentManager.beginTransaction()
            val fragmentOne = Fg2()
            val bundle = Bundle()
            bundle.putString("data", "hello from f2")
            fragmentOne.arguments = bundle
            fragmentTransaction.add(R.id.flOne, fragmentOne)
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        }
    }

}